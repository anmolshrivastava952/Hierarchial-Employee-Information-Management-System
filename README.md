# Hierarchial-Employee-Information-Management-System
Time complexity of size and tostring
---------------------------------------------------
All the functions are in log n timecomplxity other than to string and size.

Size function is not in log n because we have to travel to every node and add them to final count to get the size so the order is order of n.

For tostring function, to get all the elements in a sorted order in level an inorder traversal is done in the avl tree and when the level of node is same as that of level 
passed in the argument it is added to the string. Since the inorder traversal is called for all levels  therefore its time complexity is order of (n*no. of levels).


About node class
---------------------

The node class is a combination of a node of doubly linkedlist, avl tree and normal tree. Every node has address of its parent, its next and previous sibling,
its level and its first and last child. Node also is connected to 2 other nodes left and right which helps it to travel in avl tree. 

How operations are implemented
------------------------------------------

In insertion and deletion first we search the node which we have to delete or insert in avl tree using a search method then we change its attribute related to normal
tree and then we do the same in AVL tree. 
