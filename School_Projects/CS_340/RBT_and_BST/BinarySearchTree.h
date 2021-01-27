#pragma once
#include <string> 
#include <iomanip> 
#include <cstdlib> 
#include "BinarySearchTree.h"

using namespace std;

/*
* Author: Blake Rusteberg
* Program: Project 2 BST, RBT
* Date: 2/19/2019
* Class: BinarySearchTree.h
*/

class BinarySearchTree
{
public:
	//structure to store node information
	struct Node {
		string key;
		Node *right, *left;
	};
	// Starting node
	Node *root = NULL;
	void insert(string word);
	string searchForWord(string word);
};