package com.raimond.apcminiclient.MIDIControls;

import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.*;

public class MidiNoteControls {
	private static final Logger log = LoggerFactory.getLogger(MidiNoteControls.class);
	private MidiDevice device;
	private Transmitter transmitter;

	public void initControlResources() {
		String deviceName = "APC MINI";
		// Open the MIDI device (replace with your device name)
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (MidiDevice.Info info : infos) {
			if (info.getName().equals(deviceName)) { // Replace with your device's name
				try {
					device = MidiSystem.getMidiDevice(info);
					// Ensure the device has a transmitter (input device)
					if (device.getMaxTransmitters() != 0) {
						device.open();
//							System.out.println("Opened device transmitters: " + info.getName());
						break;
					}
				} catch (MidiUnavailableException e) {
					log.error("No suitable transmitter found for opening the device", e);
				}
			}
		}
//		Stream<MidiDevice.Info> filteredInfos = Arrays.stream(infos).filter(name -> name.getName().equals(deviceName));

		try {
			transmitter = device.getTransmitter();
		} catch (MidiUnavailableException e) {
			log.error("No suitable transmitter found for transmitter field", e);
		}
		transmitter.setReceiver(new MidiInputReceiver());
		if (device == null) {
			System.out.println("No suitable MIDI device found.");
		}

	}

	private class MidiInputReceiver implements Receiver {

		@Override
		public void send(MidiMessage message, long timeStamp) {
			if (message instanceof ShortMessage) {
				ShortMessage shortMessage = (ShortMessage) message;
				int command = shortMessage.getCommand();
				int key = shortMessage.getData1();
				int velocity = shortMessage.getData2();
				Platform.runLater(() -> updateMidiStatus(command, key, velocity));
				if (command == ShortMessage.NOTE_ON) {
					new Thread(() -> {

					}).start();
				}
			}
		}

		@Override
		public void close() {
			// Optional: Handle any cleanup here
		}
	}

	private void updateMidiStatus(int command, int key, int velocity) {
		String commandType = switch (command) {
			case ShortMessage.NOTE_ON -> "Note ON";
			case ShortMessage.NOTE_OFF -> "Note OFF";
			case ShortMessage.CONTROL_CHANGE -> "Control Change";
			default -> "Unknown Command";
		};
		String status = String.format("Command: %s | Key: %d | Velocity: %d", commandType, key, velocity);
		System.out.println(status);
	}

	public void closeControlResources() {
		try {
			if (transmitter != null) {
				transmitter.close();
				System.out.println("Transmitter closed.");
			}

			if (device != null) {
				device.close();
				System.out.println("MIDI device closed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
