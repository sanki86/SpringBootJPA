
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeRequest;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
	
public class SampleSpeech {
	
	public static void sampleRecognize() {
		
		System.out.println("We are inside ");
	  // TODO(developer): Replace these variables before running the sample.
	  String localFilePath = "C:\\Users\\muthu\\Desktop\\Google_Gnome.wav";
	  sampleRecognize(localFilePath);
	}

	/**
	 * Transcribe a short audio file using synchronous speech recognition
	 *
	 * @param localFilePath Path to local audio file, e.g. /path/audio.wav
	 */
	public static void sampleRecognize(String localFilePath) {
	  try (SpeechClient speechClient = SpeechClient.create()) {

	    // The language of the supplied audio
	    String languageCode = "en-US";

	    // Sample rate in Hertz of the audio data sent
	    int sampleRateHertz = 16000;

	    // Encoding of audio data sent. This sample sets this explicitly.
	    // This field is optional for FLAC and WAV audio formats.
	    RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.LINEAR16;
	    RecognitionConfig config =
	        RecognitionConfig.newBuilder()
	            .setLanguageCode(languageCode)
	            .setSampleRateHertz(sampleRateHertz)
	            .setEncoding(encoding)
	            .build();
	    Path path = Paths.get(localFilePath);
	    byte[] data = Files.readAllBytes(path);
	    ByteString content = ByteString.copyFrom(data);
	    RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(content).build();
	   
	    RecognizeRequest request =
	        RecognizeRequest.newBuilder().setConfig(config).setAudio(audio).build();
	    
	    RecognizeResponse response = speechClient.recognize(request);
	    
	    for (SpeechRecognitionResult result : response.getResultsList()) {
	      // First alternative is the most probable result
	      SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
	      System.out.printf("Transcript: %s\n", alternative.getTranscript());
	    }
	  } catch (Exception exception) {
	    System.err.println("Failed to create the client due to: " + exception);
	  }
	}
}
