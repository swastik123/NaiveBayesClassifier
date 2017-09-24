package assignment;

import java.util.*;
import java.io.*;

public class NaiveBayesClassifier  {

	protected static LinkedHashMap<String, LinkedHashMap<String, Double>> trainingdata = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> getTrainingdata() {
		return trainingdata;
	}
	public static void setTrainingdata(LinkedHashMap<String, LinkedHashMap<String, Double>> trainingdata) {
		NaiveBayesClassifier.trainingdata = trainingdata;
	}
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> gettestData() {
		return testData;
	}
	public static void settestData(LinkedHashMap<String, LinkedHashMap<String, Double>> testData) {
		NaiveBayesClassifier.testData = testData;
	}
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> getTrainingclassdata() {
		return trainingclassdata;
	}
	public static void setTrainingclassdata(LinkedHashMap<String, LinkedHashMap<String, Double>> trainingclassdata) {
		NaiveBayesClassifier.trainingclassdata = trainingclassdata;
	}
	public static List<String> gettestLabel() {
		return testLabel;
	}
	public static void settestLabel(List<String> testLabel) {
		NaiveBayesClassifier.testLabel = testLabel;
	}
	public static List<String> gettrainLabel() {
		return trainLabel;
	}
	public static void settrainLabel(List<String> trainLabel) {
		NaiveBayesClassifier.trainLabel = trainLabel;
	}
	public static List<String> getVocabList() {
		return vocabList;
	}
	public static void setVocabList(List<String> vocabList) {
		NaiveBayesClassifier.vocabList = vocabList;
	}
	public static List<Double> getTraining_frequencey() {
		return training_frequencey;
	}
	public static void setTraining_frequencey(List<Double> training_frequencey) {
		NaiveBayesClassifier.training_frequencey = training_frequencey;
	}
	public static List<Double> gettraining_frequence1() {
		return training_frequence1;
	}
	public static void settraining_frequence1(List<Double> training_frequence1) {
		NaiveBayesClassifier.training_frequence1 = training_frequence1;
	}
	public static ArrayList<Double> getTotalValues() {
		return totalValues;
	}
	public static void setTotalValues(ArrayList<Double> totalValues) {
		NaiveBayesClassifier.totalValues = totalValues;
	}
	public static List<String> getEnglishstopList() {
		return englishstopList;
	}
	public static void setEnglishstopList(List<String> englishstopList) {
		NaiveBayesClassifier.englishstopList = englishstopList;
	}
	public static HashMap<String, Integer> getenglishstopPosition() {
		return englishstopPosition;
	}
	public static void setenglishstopPosition(HashMap<String, Integer> englishstopPosition) {
		NaiveBayesClassifier.englishstopPosition = englishstopPosition;
	}
	public static List<String> getgrouplabels() {
		return grouplabels;
	}
	protected static LinkedHashMap<String, LinkedHashMap<String, Double>> trainingData = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
	protected static LinkedHashMap<String, LinkedHashMap<String, Double>> naiveProbability = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
	protected static LinkedHashMap<String, LinkedHashMap<String, Double>> testData  = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
	protected static LinkedHashMap<String, LinkedHashMap<String, Double>> trainingclassdata = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
	protected static List<String> testLabel = new ArrayList<String>();
	protected static List<String> trainLabel = new ArrayList<String>();
	protected static List<String> vocabList = new ArrayList<String>();
	protected static List<Double> training_frequencey = new ArrayList<Double>();
	protected static List<Double> training_frequence1 = new ArrayList<Double>();
	protected static List<String> grouplabels = new ArrayList<String>();
	protected static ArrayList<Double> totalValues = new ArrayList<Double>();
	protected static List<String> englishstopList = new ArrayList<String>();
	protected static HashMap<String, Integer> englishstopPosition = new HashMap<String, Integer>();
	protected static int withStopWord[][] = new int[20][20];
	protected static int withoutStopword[][] = new int[20][20];


	
  public NaiveBayesClassifier() {
	  
	 	  testLabel = readFile("data/test.label");
	 	  trainLabel = readFile("data/train.label");
	 	  vocabList = readFile("data/vocabulary.txt");
	 	  grouplabels = readFile("data/grouplabels.txt");
	 	  trainingdata = readDataInHashMap("data/train.data", trainLabel);
	 	  testData = readDataInHashMap("data/test.data", testLabel);
	 	  englishstopList = readFile("data/english.stop");
	    
	 	
  }
  
  private static List<String> readFile(String fileName) {
	  List<String> readData = new ArrayList<String>();
	  
	  try {
		  BufferedReader br = new BufferedReader(new FileReader(fileName));
		  for(String line = br.readLine(); line != null; line = br.readLine()) {
			  readData.add(line);
		  }
		  br.close();
		  return readData;
	  } 
	  catch(IOException e) {
		  e.printStackTrace();
		  System.exit(1);
		  return null;
	  } 
  }
  
  
 
  private static LinkedHashMap<String, LinkedHashMap<String, Double>> readDataInHashMap(String fileName, List<String> label) {
	
	LinkedHashMap<String, LinkedHashMap<String, Double>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
	
	for (int i = 0; i < label.size(); i++)
		linkedHashMap.put(Integer.toString(i+1), new LinkedHashMap<String, Double>());
	
    try {
      
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      for(String line = br.readLine(); line != null; line = br.readLine()) {
    	  String[] list1 = line.split(" ");
    	  LinkedHashMap<String, Double> hashMap1;
    	  if (linkedHashMap.containsKey(list1[0]))
    		  hashMap1 = linkedHashMap.get(list1[0]);
    	  else {
    		  hashMap1 = new LinkedHashMap<String, Double>();
    		  linkedHashMap.put(list1[0], hashMap1);
    	  }
    	  
    	  double count = 0.0;
    	  if (hashMap1.containsKey(list1[1]))
    		  count = hashMap1.get(list1[1]);
    	  hashMap1.put(list1[1], count + Double.parseDouble(list1[2]));  
      }
      br.close();
      return linkedHashMap;
      
    } 
    catch(IOException e) {
      e.printStackTrace();
      System.exit(1);
      return null;
    } 
  }
  
  public static HashMap<String, Integer> locateStopWordsPosition(List<String> vocabList, List<String> stopword) {
	  
	  HashMap<String, Integer> stopWord = new HashMap<String, Integer>();
	  for (int i = 0; i < vocabList.size(); i++) {
		  for (int j = 0; j < stopword.size(); j++) {
			  if (vocabList.get(i).toString().equals(stopword.get(j).toString()))
				  stopWord.put(Integer.toString(i+1), i );
		  }
	  }
	  
	  return stopWord;
  }
  
  public static void calculateFrequency() {
	

    for (int i = 0; i < training_frequencey.size(); i++ ) {
    	double frequency = training_frequencey.get(i) / Double.parseDouble(Integer.toString(trainLabel.size()));
    	training_frequence1.add(frequency);
    }
    
    for (int i = 0; i <20; i++) {
    	for (int j = 0; j < 20; j++ ) {
    		withStopWord[i][j] = 0;
    		withoutStopword[i][j] = 0;
    	}
    }
  }
    

  private static void train_classification(LinkedHashMap<String, LinkedHashMap<String, Double>> data, List<String> label, List<String> vocabList) {
	  
	  for (int i = 0; i < grouplabels.size(); i++ ) {
		  
		double totalValue = 0.0;
		
		ArrayList<String> position = new ArrayList<String>();
		trainingData.put(Integer.toString(i+1), new LinkedHashMap<String, Double>());
		naiveProbability.put(Integer.toString(i+1), new LinkedHashMap<String, Double>());
		
		
		for (int j = 0; j < trainLabel.size(); j++) {
			String labelFile = trainLabel.get(j);		  
			if (labelFile.equals(Integer.toString(i+1)))
				position.add(Integer.toString(j+1));
		}
	
		training_frequencey.add(Double.parseDouble(Integer.toString(position.size())));
		
		for (int j = 0; j < position.size(); j++ ) {
			
			LinkedHashMap<String, Double> single_document = data.get(position.get(j));
			Set<String> singleDocument = single_document.keySet();
			Iterator<String> it = singleDocument.iterator();
			while (it.hasNext()) {
				String word = it.next().toString();
				totalValue = totalValue + data.get(position.get(j)).get(word);
				boolean containsWord = trainingData.get(Integer.toString(i+1)).containsKey(word);  
				double count = data.get(position.get(j)).get(word);
				double existcount = 0;
				if (containsWord)
					existcount = trainingData.get(Integer.toString(i+1)).get(word);
				else
					trainingData.get(Integer.toString(i+1)).put(word, count);

				trainingData.get(Integer.toString(i+1)).put(word, count + existcount);
			  }   
		  }
		  
		  totalValues.add(totalValue);
		  
	
		  for (int k = 0; k < vocabList.size(); k++) { 
			  boolean containsWordInVocabList = trainingData.get(Integer.toString(i+1)).containsKey(Integer.toString(k+1));
			  if (containsWordInVocabList) {
				  double prob = Math.log((trainingData.get(Integer.toString(i+1)).get(Integer.toString(k+1)) + 1)/(totalValues.get(i) + vocabList.size()));
				  naiveProbability.get(Integer.toString(i+1)).put(Integer.toString(k+1), prob);
			  }
			  else {
				  double prob = Math.log(1 / (totalValues.get(i) + vocabList.size()));
				  naiveProbability.get(Integer.toString(i+1)).put(Integer.toString(k+1), prob);
			  }
		  }
	  }
  }
  
  
  private static Double test_classification(LinkedHashMap<String, LinkedHashMap<String, Double>> data, List<String> label,  List<Double> wordFrequency, boolean flag) {
	  
	  Integer datasize = data.size();
	  double error = 0;
	  
	  for (int i = 0; i < datasize; i++) {
		  ArrayList<Double> classProbabilityList = new ArrayList<Double>();
		  
		  for (int j = 0; j < naiveProbability.size(); j++ ) {
			  LinkedHashMap<String, Double> singledocument = data.get(Integer.toString(i+1));
			  Set<String> singleDocument = singledocument.keySet();
			  Iterator<String> it = singleDocument.iterator();
			  double probability = Math.log(wordFrequency.get(j));
			  
			  while (it.hasNext()) {
				  String word = it.next().toString();
				  boolean containsWord = englishstopPosition.containsKey(word);
				  if (flag && containsWord) {  
				  }
				  else
					  probability = probability + naiveProbability.get(Integer.toString(j+1)).get(word);
			  }
			  classProbabilityList.add(probability);
		  }
		  
		  Object obj = Collections.max(classProbabilityList);
		  String predicte_class1 = obj.toString();
		  int index = classProbabilityList.indexOf(Double.parseDouble(predicte_class1));
		  String predicte_class = Integer.toString(index + 1);

		
		  if (!predicte_class.equals(label.get(i))) {
			  error = error + 1;
			  if (flag)
				  withoutStopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = withoutStopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			
		  }
		  else
		  {
			  if (flag)
				  withoutStopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = withoutStopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			
		  }  
	  }
	  return (1-error/label.size());
  }
  
 
   public static void testingData() {
	  System.out.println("Starting Testing of Data");
	  
	  double multinomialaccuray = test_classification(testData, testLabel, training_frequence1, true);
	  System.out.println("accuracy: " + multinomialaccuray*100 );
  	}
  
  public static void main(String args[]) throws IOException {
		NaiveBayesClassifier nbc = new NaiveBayesClassifier();
		nbc.englishstopPosition = locateStopWordsPosition(vocabList, englishstopList);
	 	  System.out.println("Starting Training of Data");
		 nbc.train_classification(trainingdata, trainLabel, vocabList);
		 nbc.calculateFrequency();
		 nbc.testingData();
	}
}