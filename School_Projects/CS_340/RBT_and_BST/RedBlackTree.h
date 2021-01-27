#pragma once
#include <string> 
#include <iomanip> 
#include <cstdlib> 

using namespace std;

/*
* Author: Blake Rusteberg
* Program: Project 2 BST, RBT
* Date: 2/19/2019
* Class: RedBlackTree.h
*/

class RedBlackTree
{
public:
	string color;
	//structure to store node information
	struct Node {
		string key;
		bool red;
		Node *right, *left, *parent;
	};
	Node *root = new Node();
	Node *nil = new Node();

	void setNil();
	void setTree();

	void setInsert(Node *z);
	void insert(string word);
	void insertFixup(Node *current, Node *z);
	void leftRotate(Node *current, Node *z);
	void rightRotate(Node *current, Node *z);
	string searchForWord(string findWord);

};