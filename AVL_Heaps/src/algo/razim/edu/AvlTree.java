package algo.razim.edu;

import java.util.Queue;

public class AvlTree<T extends Comparable<T>>
{
	Node<T> root;
	
	public boolean offer(T data)
	{
		boolean didOffer = false;
		
		if(root == null)
		{
			root = new Node<T>();
			root.setData(data);
			
			didOffer =  true;
		}
		else
		{
			didOffer = offerHelper(data, root);
		}
		
		if(didOffer)
		{
			checkForBalance(root, null);
		}
		
		return didOffer;
		
	}
	
	private boolean offerHelper(T data, Node<T> node)
	{
		if(node == null)
		{
			node = new Node<T>();
			node.setData(data);
			
			return true;
		}
		
		if(data.compareTo(node.getData()) < 0)
		{
			if(node.getLeft() == null)
			{
				Node<T> newNode = new Node<T>();
				newNode.setData(data);
				node.setLeft(newNode);
				
				return true;
			}
			else
			{
				offerHelper(data, node.getLeft());
			}
		}
		else if(data.compareTo(node.getData()) > 0)
		{
			if(node.getRight() == null)
			{
				Node<T> newNode = new Node<T>();
				newNode.setData(data);
				node.setRight(newNode);
				
				return true;
			}
			else
			{
				offerHelper(data, node.getRight());
			}
		}
		else //no dupes
		{
			return false;
		}
		
		return true;
	}
	
	private int height(Node<T> node)
	{
		if(node == null)
		{
			return 0;
		}
		if(isLeaf(node))
		{
			return 1;
		}
		
		return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
	}
	
	private void checkForBalance(Node<T> node, Node<T> parent)
	{
		if(node == null)
		{
			return;
		}
		checkForBalance(node.getLeft(), node);
		checkForBalance(node.getRight(), node);
		
		if(balanceFactor(node) >= 2)
		{
			rebalance(node, parent);
		}
		if(balanceFactor(node) <= -2)
		{
			rebalance(node, parent);
		}
	}
	
	private void rebalance(Node<T> node, Node<T> parent)
	{
		if(balanceFactor(node) > 0)//left
		{
			if(balanceFactor(node.getLeft()) < 0)//kink
			{
				leftRotation(node.getLeft(), node);
				rightRotation(node, parent);
			}
			else//stick
			{
				rightRotation(node, parent);
			}
		}
		else//right
		{
			if(balanceFactor(node.getRight()) > 0)//kink
			{
				rightRotation(node.getRight(), node);
				leftRotation(node, parent);
			}
			else//stick
			{
				leftRotation(node, parent);
			}			
		}		
	}
	
	private void rightRotation(Node<T> node, Node<T> parent)
	{		
		Node<T> pivot = node.getLeft();
		if(parent != null && parent.getRight() == node)
		{
			parent.setRight(pivot);
		}
		else if(parent != null)
		{
			parent.setLeft(pivot);
		}
		
		node.setLeft(pivot.getRight());
		pivot.setRight(node);
		
		if(parent == null)
		{
			root = pivot;
		}
	}
	
	private void leftRotation(Node<T> node, Node<T> parent)
	{
		Node<T> pivot = node.getRight();
		if(parent != null && parent.getRight() == node)
		{
			parent.setRight(pivot);
		}
		else if(parent != null)
		{
			parent.setLeft(pivot);
		}
		
		node.setRight(pivot.getLeft());
		pivot.setLeft(node);
		
		if(parent == null)
		{
			root = pivot;
		}
	}
	
	private int balanceFactor(Node<T> node)
	{
		return height(node.getLeft()) - height(node.getRight());
	}
	
	private boolean isLeaf(Node<T> node)
	{
		return node.getLeft() == null && node.getRight() == null;
	}
	
	public T poll()
	{
		Node<T> parent = root;
		Node<T> temp = root;
		T data = null;
		
		if(root != null)
		{
			if(isLeaf(root))
			{
				Node<T> toReturn = root;
				root = null;
				return toReturn.getData();
			}
			
			while(temp.getLeft() != null)
			{
				parent = temp;
				temp = temp.getLeft();
			}
			
			if(temp == root)
			{
				data = temp.getData();
				root = root.getRight();
			}
			parent.setLeft(temp.getRight());
			
			data = temp.getData();
			
			checkForBalance(root, null);
		}
		
		return data;
	}
	
	public T peek()
	{
		Node<T> temp = root;
		
		while(temp.getLeft() != null)
		{
			temp = temp.getLeft();
		}
		
		return temp.getData();
	}
	
	String preOrderString = "";
	
	public String preOrderToString()
	{		
		preOrderString = "";
		preOrder(root);
		return preOrderString;
	}
	
	private void preOrder(Node<T> node)
	{
		preOrderString += node.getData().toString() + ",";
		if(node.getLeft() != null)
		{
			preOrder(node.getLeft());
		}
		if(node.getRight() != null)
		{
			preOrder(node.getRight());
		}
	}
	
	String inOrderString = "";
	
	public String inOrderToString()
	{
		inOrderString = "";
		inOrder(root);
		return inOrderString;
	}
	
	public void inOrder(Node<T> node)
	{
		if(node.getLeft() != null)
		{
			inOrder(node.getLeft());
		}
		inOrderString += node.getData().toString();
		if(node.getRight() != null)
		{
			inOrder(node.getRight());
		}
	}
	
	String postOrderString = "";
	
	public String postOrderToString()
	{
		postOrderString = "";
		postOrder(root);
		return postOrderString;
	}
	
	public void postOrder(Node<T> node)
	{
		if(node.getLeft() != null)
		{
			postOrder(node.getLeft());
		}
		if(node.getRight() != null)
		{
			postOrder(node.getRight());
		}
		postOrderString += node.getData().toString();
	}
	
	public int size()
	{
		return sizeHelper(root);
	}
	
	private int sizeHelper(Node<T> node)
	{
		if(node == null)
		{
			return 0;
		}
		return sizeHelper(node.getLeft()) + sizeHelper(node.getRight()) + 1;
	}
}
