package com.raimond.apcminiclient.MIDIControls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.*;

public class MidiNoteColor {
	private static final Logger log = LoggerFactory.getLogger(MidiNoteColor.class);
	private final int DEFAULT_CHANNEL = 0;

	private MidiDevice device2;
	private Receiver receiver;

	private void initColorResources() {
		try {
			String deviceName = "APC MINI";
			// Open the MIDI device (replace with your device name)
			MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

			// for color
			for (MidiDevice.Info info : infos) {
				// Check if the device is the correct one (e.g., "APC MINI")
				if (info.getName().equals(deviceName)) { // Replace with your device name
					device2 = MidiSystem.getMidiDevice(info);


					// Check if the device has a receiver (for sending data to the controller)
					if (device2.getMaxReceivers() != 0) {
						device2.open();
						receiver = device2.getReceiver();
						System.out.println("Opened device receivers: " + info.getName());
						// Now you can send messages to control LEDs/colors
					} else {
						System.out.println("This device does not support receiving MIDI messages.");
					}
					break; // Stop after finding and opening the correct device
				}
			}
			// MIDI channel (0-15)
			if (device2 == null) {
				System.out.println("No suitable MIDI device found.");
			}

		} catch (MidiUnavailableException e) {
			log.error("No midi device found, could not have initialized the colors.", e);
		}
	}

	public void setColorOff(int note) {
		initColorResources();
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(ShortMessage.NOTE_ON,DEFAULT_CHANNEL,note,0);
		} catch (InvalidMidiDataException e){
			log.error("Unknown note id: {}", note, e);
		}
		receiver.send(msg, -1);
		closeColorResources();
	}

	public void setColorRed(int note) {
		initColorResources();
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(ShortMessage.NOTE_ON,DEFAULT_CHANNEL,note,3);
		} catch (InvalidMidiDataException e) {
			log.error("Unknown note id: {}", note, e);
		}
		receiver.send(msg, -1);
		closeColorResources();
	}

	public void setColorRedBlinking(int note) {
		initColorResources();
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(ShortMessage.NOTE_ON,DEFAULT_CHANNEL,note,4);
		} catch (InvalidMidiDataException e) {
			log.error("Unknown note id: {}", note, e);
		}
		receiver.send(msg, -1);
		closeColorResources();
	}

	public void setColorGreen(int note) {
		initColorResources();
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(ShortMessage.NOTE_ON,DEFAULT_CHANNEL,note,1);
		} catch (InvalidMidiDataException e) {
			log.error("Unknown note id: {}", note, e);
		}
		receiver.send(msg, -1);
		closeColorResources();
	}

	public void setColorGreenBlinking(int note) {
		initColorResources();
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(ShortMessage.NOTE_ON,DEFAULT_CHANNEL,note,2);
		} catch (InvalidMidiDataException e) {
			log.error("Unknown note id: {}", note, e);
		}
		receiver.send(msg, -1);
		closeColorResources();
	}

	public void setColorYellow(int note) {
		initColorResources();
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(ShortMessage.NOTE_ON,DEFAULT_CHANNEL,note,5);
		} catch (InvalidMidiDataException e) {
			log.error("Unknown note id: {}", note, e);
		}
		receiver.send(msg, -1);
		closeColorResources();
	}

	public void setColorYellowBlinking(int note) {
		initColorResources();
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(ShortMessage.NOTE_ON,DEFAULT_CHANNEL,note,6);
		} catch (InvalidMidiDataException e) {
			log.error("Unknown note id: {}", note, e);
		}
		receiver.send(msg, -1);
		closeColorResources();
	}

	private void closeColorResources() {
		if (receiver != null) {
			receiver.close();
			System.out.println("Receiver closed.");
		}

		if (device2 != null) {
			device2.close();
			System.out.println("device2 closed.");
		}

	}
}
