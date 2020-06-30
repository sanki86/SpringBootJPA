package com.test.textToSpeech;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.common.html.HtmlEscapers;
import com.google.protobuf.ByteString;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javazoom.jl.player.Player;

public class ssmlConversion {
	
	   // String inputFile = "Hello Arush hello Aditi How are you";
	   // String outFile = "resources/example.mp3";
	    
	public static String textToSsml(String inputFile) throws Exception {

	    // Read lines of input file
	    String rawLines = new String(inputFile);

	    // Replace special characters with HTML Ampersand Character Codes
	    // These codes prevent the API from confusing text with SSML tags
	    // For example, '<' --> '&lt;' and '&' --> '&amp;'
	    String escapedLines = HtmlEscapers.htmlEscaper().escape(rawLines);

	    // Convert plaintext to SSML
	    // Tag SSML so that there is a 2 second pause between each address
	    String expandedNewline = escapedLines.replaceAll("\\n", "\n<break time='2s'/>");
	    String ssml = "<speak>" + expandedNewline + "</speak>";

	    System.out.println("ssml result is"+ssml);
	    // Return the concatenated String of SSML
	    return ssml;
	  }
	
	
	public static void ssmlToAudio(String ssmlText) throws Exception {
	    // Instantiates a client
	    try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
	      // Set the ssml text input to synthesize
	      SynthesisInput input = SynthesisInput.newBuilder().setSsml(ssmlText).build();

	      // Build the voice request, select the language code ("en-US") and
	      // the ssml voice gender ("male")
	      VoiceSelectionParams voice =
	          VoiceSelectionParams.newBuilder()
	              .setLanguageCode("en-US")
	              .setSsmlGender(SsmlVoiceGender.FEMALE)
	              .build();

	      // Select the audio file type
	      AudioConfig audioConfig =
	          AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();

	      // Perform the text-to-speech request on the text input with the selected voice parameters and
	      // audio file type
	      SynthesizeSpeechResponse response =
	          textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

	      // Get the audio contents from the response
	      ByteString audioContents = response.getAudioContent();
          Player playMp3;
         
	      // Write the response to the output file
	     // try (OutputStream out = new FileOutputStream(outFile)) {
	       // out.write(audioContents.toByteArray());
	        InputStream  inStream= new ByteArrayInputStream(audioContents.toByteArray());
	        playMp3 =new Player(inStream);
	        playMp3.play();
	       // System.out.println("Audio content written to file " + outFile);
	        
	        
	      //}
	    }
	  }
	
	public  void basic(String inputFile) throws Exception{
	    // test example address file
		String ssml=textToSsml(inputFile);	   

	    
	    ssmlToAudio(ssml);
	  }
}
