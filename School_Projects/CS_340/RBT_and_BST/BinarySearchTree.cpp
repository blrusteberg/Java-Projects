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
* Class: BinarySearchTree.cpp
*/

void BinarySearchTree::insert(string word) {
	Node *temp = new Node;
	temp->key = word;
	temp->left = NULL;
	temp->right = NULL;

	if (root == NULL)
	{
		root = temp;
	}
	else
	{
		Node *current = root;
		Node *parent = NULL;
		while (current != NULL)
		{
			parent = current;
			if (temp->key > current->key)
			{
				current = current->right;
			}
			else
			{
				current = current->left;
			}
		}
		if (temp->key > parent->key)
		{
			parent->right = temp;
		}
		else
		{
			parent->left = temp;
		}
	}
}

string BinarySearchTree::searchForWord(string findWord) {
	Node *current = root;

	// search until you reach the bottom of tree, unless the word is found
	while (current != NULL)
	{
		if (current->key == findWord)
		{
			return current->key;
		}
		if (current->key < findWord)
		{
			current = current->right;
		}
		else
		{
			current = current->left;
		}
	}
	return "NULL";
}
