# NaiveBayesClassifier

This assignment is focussed on building the NaiveBayes Classification Algorithm.

Run the .java program using any IDE like Eclipse
No Arguments need to be passed as we have created separate folder called Data in eclipse and store all the training data and test  data.
-------------------------------------------------------
20news-bydate-test folder in Data folder contains all the test data.
20news-bydate-train in Data folder contains all the train data.

data folder contain several other .txt file,which are:
1)english.stop contains the list of stop word,which we have compared with train and test data and remove the stop words to get better accuracy.
2)grouplabels.txt contains the  label name of train and test data folder. example

alt.atheism
comp.graphics
comp.os.ms-windows.misc
comp.sys.ibm.pc.hardware
comp.sys.mac.hardware
comp.windows.x
misc.forsale
rec.autos
rec.motorcycles
rec.sport.baseball
rec.sport.hockey
sci.crypt
sci.electronics
sci.med
sci.space
soc.religion.christian
talk.politics.guns
talk.politics.mideast
talk.politics.misc
talk.religion.misc

3)test.label contains the test label id
4)train.data contains the train label id.
5)vocabulary.txt contains list of all the words of indexed data.
6)train.data contains docId,wordId,count of words in training data
7)test.data contains docId,wordId,count of words in testing data


We are getting 80.61292471685543 accuracy.

