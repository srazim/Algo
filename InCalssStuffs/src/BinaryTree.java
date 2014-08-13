import java.util.ArrayList;


public class BinaryTree 
{
	Node root;

	void Add(String newValue)
	{
		if(root == null)
		{
			root = new Node();
			root.value = newValue;
		}
		else
		{
			Add(root, newValue);
		}
	}

	private void Add(Node node, String newValue)
	{
		if(node == null)
		{
			node = new Node();
			node.value = newValue;
		}
		else if(node.value.compareTo(newValue) < 0)
		{
			if(node.right == null)
			{
				node.right = new Node();
				node.right.value = newValue;
			}
			else
			{
				Add(node.right, newValue);
			}
		}
		else if(node.value.compareTo(newValue) >= 0)
		{
			if(node.left == null)
			{
				node.left = new Node();
				node.left.value = newValue;
			}
			else
			{
				Add(node.left, newValue);
			}
		}
	}

	boolean Contains(String name)
	{
		return Contains(root, name);
	}

	private boolean Contains(Node node, String name)
	{
		if(node == null)
		{
			return false;
		}
		else if(node.value.compareTo(name) < 0)
		{
			return Contains(node.right, name);
		}
		else if(node.value.compareTo(name) > 0)
		{
			return Contains(node.left, name);
		}

		return true;
	}

	boolean Remove(String name)
	{
		//the node to be deleted
		Node target = null;
		//to keep track of parent node
		Node parent = null;
		//variable node reference
		Node node = root;

		while (node != null)
		{
			if (name.compareTo( node.value ) == 0)  //found node to delete
			{
				target = node;
				break;
			}
			else if (name.compareTo( node.value ) > 0)  //target greater, go right
			{
				parent = node;
				node = node.right;
			}
			else    //target less, go left
			{
				parent = node;
				node = node.left;
			}
		}

		if (target == null)
		{
			//target not found
			return false;
		}

		boolean isLeft = (target == parent.left );

		if (target == root) //the node that's being deleted is the root node
		{
			//get last name on the left on the right!
			//it becomes the new root
			node = getLastNameOnTheLeft( parent.right );
			if (node != null)
			{
				node.left = parent.left;
				node.right = parent.right;
				root = node;
			}
		}
		else if ( (target.left == null && target.right == null) )
		{
			if (isLeft)
			{
				parent.left = null;
			}
			else
			{
				parent.right = null;
			}
		}
		else if (target.left != null && target.right != null) //two children, some shuffling
		{
			if (isLeft)
			{
				parent.left = target.right;
				parent.left.left = target.left;
			}
			else
			{
				parent.right = target.right;
				parent.right.left = target.left;
			}
		}
		else    //one child
		{
			if (target.left == null)
			{
				if (isLeft)
				{
					parent.left = target.left;
				}
				else
				{
					parent.right = target.left;
				}
			}
			else
			{
				if (isLeft)
				{
					parent.left = target.right;
				}
				else
				{
					parent.right = target.right;
				}
			}
		}

		return true;
	}

	private Node getLastNameOnTheLeft(final Node start)
	{
		Node candidate = null;
		Node parent = null;
		Node node = start;

		while (node != null)
		{
			if ( node.left != null )
			{
				parent = node;
				candidate = node.left;
			}

			node = node.left;
		}

		if (parent != null)
		{
			parent.left = null;
		}

		return candidate;
	}


	int Height()
	{
		return Height(root);
	}

	private int Height(Node root)
	{
		if(root == null)
		{
			return -1;
		}

		return Math.max(Height(root.left), Height(root.right)) + 1;
	}

	int Count()
	{
		return Count(root) + 1;
	}

	private int Count(Node node)
	{
		int count = 0;

		if(node == null)
		{
			return -1;
		}

		count += Count(node.left) + 1;
		count += Count(node.right) + 1;

		return count;
	}

	ArrayList<String> inOrder()
	{		
		inOrder(root);
		return inOrderToReturn;
	}

	ArrayList<String> inOrderToReturn = new ArrayList<String>();

	private void inOrder(Node node)
	{		
		if (node != null)
		{
			inOrder(node.left);
			inOrderToReturn.add(node.value);
			inOrder(node.right);
		}
	}

	ArrayList<String> preOrderToReturn = new ArrayList<String>();

	ArrayList<String> preOrder()
	{
		preOrder(root);
		return preOrderToReturn;		
	}

	private void preOrder(Node node)
	{
		if (node != null)
		{
			preOrderToReturn.add(node.value);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	ArrayList<String> children = new ArrayList<String>();
	
	ArrayList<String> getChildren(String name)
	{
		getChildren(get(name));
		children.remove(name);
		return children;
	}
	
	private void getChildren(Node node)
	{
		if (node != null)
		{
			children.add(node.value);
			getChildren(node.left);
			getChildren(node.right);
		}
	}
	
	Node get(String name)
	{
		return get(root, name);
	}

	private Node get(Node node, String name)
	{
		if(node == null)
		{
			return null;
		}
		else if(node.value.compareTo(name) < 0)
		{
			return get(node.right, name);
		}
		else if(node.value.compareTo(name) > 0)
		{
			return get(node.left, name);
		}

		return node;
	}
	
	ArrayList<String> postOrderToReturn = new ArrayList<String>();
	
	ArrayList<String> postOrder()
	{
		postOrder(root);
		return postOrderToReturn;
	}
	
	private void postOrder(Node node)
	{
		if (node != null)
		{
			postOrder(node.left);
			postOrder(node.right);
			postOrderToReturn.add(node.value);
		}
	}
}
