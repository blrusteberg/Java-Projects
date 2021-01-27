#include <iostream> 
#include <fstream> 
#include <string> 
#include <iomanip> 
#include <cstdlib> 
#include <chrono>
#include <conio.h>
#include "BinarySearchTree.h"
#include "RedBlackTree.h"

using namespace std;
using namespace std::chrono;

/*
* Author: Blake Rusteberg
* Program: Project 2 BST, RBT
* Date: 2/19/2019
* Class: Main
*/

int main()
{
	string inputFile;
	string inputAlgorithm;
	string inputForWord;
	string buildWordsBST;
	string buildWordRBT;

	ifstream inFile;

	string combineWords[3];   //Create 3 Strings for the output file

	cout << "Which file would you like to search from? | Ex: perm15K or sorted15K |" << endl;
	cin >> combineWords[0];

	combineWords[1] = ".txt";

	combineWords[2] = combineWords[0] + combineWords[1];

	inputFile = combineWords[2];

	cout << "Which search algorithm would you like to use? | Ex: BST, RBT |" << endl;
	cin >> inputAlgorithm;

	cout << "Enter the word you would like to find? | ENTER IN ALL CAPS |" << endl;
	cin >> inputForWord;

	inFile.open(inputFile);
	if (inFile.fail()) {
		cerr << "File did not open." << endl;
	}

	if (inputAlgorithm == "BST")
	{
		BinarySearchTree BST;
		double convertToMilliSearch;
		double convertToMilliBuild;

		auto startBuild = high_resolution_clock::now();
		while (!(inFile.eof()))
		{
			inFile >> buildWordsBST;
			BST.insert(buildWordsBST);
		}
		auto stopBuild = high_resolution_clock::now();

		auto startSearch = high_resolution_clock::now();
		string found = BST.searchForWord(inputForWord);
		auto stopSearch = high_resolution_clock::now();

		if (found != "NULL") {
			cout << "Word found: " << inputForWord << endl << endl;
		}
		else {
			cout << "Word not found. " << endl << endl;
		}

		auto buildTime = duration_cast<microseconds>(stopBuild - startBuild);
		auto searchTime = duration_cast<microseconds>(stopSearch - startSearch);

		convertToMilliBuild = buildTime.count() / 1000.000;
		convertToMilliSearch = searchTime.count() / 1000.000;

		cout << "It took " << convertToMilliBuild << " milliseconds to build the Binary Search Tree." << endl;
		cout << "It took " << convertToMilliSearch << " milliseconds to find the word " << inputForWord << "." << endl;
		_getch();

	}
	else if (inputAlgorithm == "RBT")
	{
		RedBlackTree RBT;

		RBT.setNil();
		RBT.setTree();

		double convertToMilliSearch;
		double convertToMilliBuild;

		auto startBuild = high_resolution_clock::now();
		while (!(inFile.eof()))
		{
			inFile >> buildWordRBT;
			RBT.insert(buildWordRBT);
		}
		auto stopBuild = high_resolution_clock::now();

		auto startSearch = high_resolution_clock::now();
		string found = RBT.searchForWord(inputForWord);
		auto stopSearch = high_resolution_clock::now();

		if (found != "NULL") {
			cout << "Word found: " << inputForWord << endl << endl;
		}
		else {
			cout << "Word not found. " << endl << endl;
		}

		auto buildTime = duration_cast<microseconds>(stopBuild - startBuild);
		auto searchTime = duration_cast<microseconds>(stopSearch - startSearch);

		convertToMilliBuild = buildTime.count() / 1000.000;
		convertToMilliSearch = searchTime.count() / 1000.000;

		cout << "It took " << convertToMilliBuild << " milliseconds to build the Red-Black tree." << endl;
		cout << "It took " << convertToMilliSearch << " milliseconds to find the word " << inputForWord << "." << endl;
		_getch();
	}


}


