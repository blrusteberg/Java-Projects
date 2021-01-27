#pragma once
#include <string> 
#include <iomanip> 
#include <cstdlib> 
#include "RedBlackTree.h"
using namespace std;

/*
* Author: Blake Rusteberg
* Program: Project 2 BST, RBT
* Date: 2/19/2019
* Class: RedBlackTree.cpp
*/

void RedBlackTree::setNil()
{
	nil->right = nil;
	nil->left = nil;
	nil->red = false;
	nil->parent = nil;
}

void RedBlackTree::setTree()
{
	root = nil;
}

void RedBlackTree::insert(string word)
{
	Node *z = new Node();
	z->key = word;
	setInsert(z);
}

void RedBlackTree::setInsert(Node *z)
{
	Node *next = nil;
	Node *current = root;

	while (current != nil)
	{
		next = current;
		if (z->key < current->key)
		{
			current = current->left;
		}
		else
		{
			current = current->right;
		}
	}

	z->parent = nil;

	if (next == nil)
	{
		root = z;
	}
	else if (z->key < next->key)
	{
		next->left = z;
	}
	else
	{
		next->right = z;
	}

	z->left = nil;
	z->right = nil;
	z->red = true;

	insertFixup(current, z);
}

void RedBlackTree::insertFixup(Node *current, Node *z) {
	while (z->parent->red)
	{
		if (z->parent == z->parent->parent->left)
		{
			Node *next = z->parent->parent->right;
			if (next->red)
			{
				z->parent->red = false;
				next->red = false;
				z->parent->parent->red = true;
				z = z->parent->parent;
			}
			else if (z == z->parent->right)
			{
				z = z->parent;
				leftRotate(current, z);
			}
			else
			{
				z->parent->red = false;
				z->parent->parent->red = true;
				rightRotate(current, z->parent->parent);
			}
		}
		else
		{
			Node *next = z->parent->parent->left;
			if (next->red)
			{
				z->parent->red = false;
				next->red = false;
				z->parent->parent->red = true;
				z = z->parent->parent;
			}
			else if (z == z->parent->left)
			{
				z = z->parent;
				rightRotate(current, z);
			}
			else
			{
				z->parent->red = false;
				z->parent->parent->red = true;
				leftRotate(current, z->parent->parent);
			}
		}
	}
	current->red = false;
}

void RedBlackTree::rightRotate(Node *current, Node *z) {
	Node *next = z->left;
	z->left = next->right;

	if (next->right != nil)
	{
		next->right->parent = z;
	}

	next->parent = z->parent;

	if (z->parent == nil)
	{
		current = next;
	}
	else if (z == z->parent->right)
	{
		z->parent->right = next;
	}
	else
	{
		z->parent->left = next;
	}

	next->right = z;
	z->parent = next;
}

void RedBlackTree::leftRotate(Node *current, Node *z) {
	Node *next = z->right;
	z->right = next->left;

	if (next->left != nil)
	{
		next->left->parent = z;
	}
	next->parent = z->parent;
	if (z->parent == nil)
	{
		current = next;
	}
	else if (z == z->parent->left)
	{
		z->parent->left = next;
	}
	else
	{
		z->parent->right = next;
	}

	next->left = z;
	z->parent = next;
}

string RedBlackTree::searchForWord(string findWord) {
	Node *current = root;

	while (current != nil)
	{
		if (current->key == findWord)
		{
			return current->key;
		}
		if (findWord < current->key)
		{
			current = current->left;
		}
		else
		{
			current = current->right;
		}
	}
	return "NIL";
}