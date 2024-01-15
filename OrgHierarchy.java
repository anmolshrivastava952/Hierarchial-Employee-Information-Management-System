
class Node {
	public int id;
	public Node left;
	public Node right;
	public Node firstchild;
	public Node nextsibling;
	public Node prevsibling;
	public Node lastchild;
	public Node parent;
	public int level;
	public int height;

	public Node(int data) {
		id = data;
		left = null;
		right = null;
		firstchild = null;
		nextsibling = null;
		lastchild = null;
		parent = null;
		level = 1;
		height = 1;
		prevsibling = null;
	}

}

public class OrgHierarchy implements OrgHierarchyInterface {

	// root11 node
	Node root1 = new Node(0);
	Node root2 = new Node(0);

	public boolean isEmpty() {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		return (root1.firstchild == null);
	}

	int size(Node root2) {
		if (root2 == null)
			return 0;
		int smallans1 = size(root2.left);
		int smallans2 = size(root2.right);
		return smallans1 + smallans2 + 1;

	}

	public int size() {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		return size(root2.firstchild);
	}

	Node search(Node root2, int id) {
		if (root2 == null) {
			return null;
		} else if (id == root2.id) {
			return root2;
		} else if (id > root2.id)
			return search(root2.right, id);
		else
			return search(root2.left, id);

	}

	public int level(int id) throws IllegalIDException {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		Node N = search(root2.firstchild, id);
		if (N == null) {
			throw new IllegalIDException("E");
		}
		return N.level;
	}

	public void hireOwner(int id) throws NotEmptyException {

		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		if (!isEmpty()) {
			throw new NotEmptyException("E");
		}
		Node N = new Node(id);
		N.level = 1;
		root1.firstchild = N;
		root2.firstchild = N;
	}

	int balance(Node root) {
		if (root == null)
			return 0;
		return getheight(root.left) - getheight(root.right);
	}

	int getheight(Node root) {
		if (root == null)
			return 0;
		else
			return root.height;
	}

	Node rotateleft(Node root) {
		Node right1 = root.right;
		root.right = right1.left;
		right1.left = root;
		root.height = getheight(root.left) >= getheight(root.right) ? getheight(root.left) + 1
				: getheight(root.right) + 1;
		right1.height = getheight(right1.right) >= getheight(right1.left) ? getheight(right1.right) + 1
				: getheight(right1.left) + 1;
		return right1;
	}

	Node rotateright(Node root) {
		Node left1 = root.left;
		root.left = left1.right;
		left1.right = root;
		root.height = getheight(root.left) >= getheight(root.right) ? getheight(root.left) + 1
				: getheight(root.right) + 1;
		left1.height = getheight(left1.left) >= getheight(left1.right) ? getheight(left1.left) + 1
				: getheight(left1.right) + 1;
		return left1;

	}

	Node succ(Node root) {
		Node temp1 = root;
		while (temp1.left != null)
			temp1 = temp1.left;
		return temp1;
	}

	Node insertinAVL(Node newbee, Node root2) {
		if (root2 == null)
			return newbee;

		if (newbee.id < root2.id) {
			root2.left = insertinAVL(newbee, root2.left);
			if (balance(root2) == 2)
				if (newbee.id < root2.left.id)
					root2 = rotateright(root2);
				else {
					root2.left = rotateleft(root2.left);
					root2 = rotateright(root2);
				}

		} else {
			root2.right = insertinAVL(newbee, root2.right);
			if (balance(root2) == -2)
				if (newbee.id > root2.right.id)
					root2 = rotateleft(root2);
				else {
					root2.right = rotateright(root2.right);
					root2 = rotateleft(root2);
				}
		}
		root2.height = getheight(root2.left) > getheight(root2.right) ? getheight(root2.left) + 1
				: getheight(root2.right) + 1;
		return root2;
	}

	Node DeleteinAVL(Node root2, Node N) {
		if (root2 == null) {
			return null;
		} else if (root2.id > N.id) {
			root2.left = DeleteinAVL(root2.left, N);
		} else if (root2.id < N.id) {
			root2.right = DeleteinAVL(root2.right, N);
		} else {
			if (root2.left == null || root2.right == null) {
				if (root2.left == null) {
					root2 = root2.right;
				} else {
					root2 = root2.left;
				}
			} else {
				Node succesor = succ(root2.right);
				Node temp = succesor;
				root2.right = DeleteinAVL(root2.right, succesor);
				Node right1 = root2.right;
				Node left1 = root2.left;
				root2 = temp;
				root2.left = left1;
				root2.right = right1;
			}

		}
		if (root2 == null) {
			return root2;
		}
		root2.height = getheight(root2.left) > getheight(root2.right) ? getheight(root2.left) + 1
				: getheight(root2.right) + 1;
		int val = balance(root2);
		if (val > 1) {
			if (balance(root2.left) >= 0)
				return rotateright(root2);
			else {
				root2.left = rotateleft(root2.left);
				return rotateright(root2);
			}
		}
		if (val < -1) {
			if (balance(root2.right) <= 0) {
				return rotateleft(root2);
			} else {
				root2.right = rotateright(root2.right);
				return rotateleft(root2);
			}
		}
		return root2;
	}

	public void hireEmployee(int id, int bossid) throws IllegalIDException {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		Node N = search(root2.firstchild, bossid);
		if (N == null) {
			throw new IllegalIDException("E");
		}
		Node newbee = new Node(id);
		newbee.level = N.level + 1;
		newbee.parent = N;
		if (N.firstchild == null && N.lastchild == null) {
			N.firstchild = newbee;
			N.lastchild = newbee;
		} else {
			N.lastchild.nextsibling = newbee;
			newbee.prevsibling = N.lastchild;
			N.lastchild = newbee;
		}
		root2.firstchild = insertinAVL(newbee, root2.firstchild);
	}

	public void fireEmployee(int id) throws IllegalIDException {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		Node N = search(root2.firstchild, id);
		if (N == null)
			throw new IllegalIDException("E");
		if (N.parent != null) {

			if (N != N.parent.firstchild && N != N.parent.lastchild) {
				N.prevsibling.nextsibling = N.nextsibling;
				N.nextsibling.prevsibling = N.prevsibling;
			} else if (N == N.parent.firstchild && N != N.parent.lastchild) {
				N.parent.firstchild = N.nextsibling;
				N.nextsibling.prevsibling = null;
			} else if (N == N.parent.lastchild && N != N.parent.firstchild) {
				N.parent.lastchild = N.prevsibling;
				N.prevsibling.nextsibling = null;
			} else {
				N.parent.firstchild = null;
				N.parent.lastchild = null;
			}

			root2.firstchild = DeleteinAVL(root2.firstchild, N);
		} else
			throw new IllegalIDException("E");

	}

	public void fireEmployee(int id, int manageid) throws IllegalIDException {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		Node fire = search(root2.firstchild, id);
		Node newboss = search(root2.firstchild, manageid);
		if (fire == null || newboss == null) {
			throw new IllegalIDException("E");
		}
		Node temp = fire.firstchild;
		while (temp != null) {
			temp.parent = newboss;
			if (newboss.firstchild == null) {
				newboss.firstchild = temp;
				newboss.lastchild = temp;
			} else {
				newboss.lastchild.nextsibling = temp;
				if (temp.prevsibling == null) {
					temp.prevsibling = newboss.lastchild;
				}
				newboss.lastchild = temp;
			}
			temp = temp.nextsibling;
		}
		fire.firstchild = null;
		fire.lastchild = null;
		if (fire.parent != null) {
			if (fire.parent.firstchild == fire && fire.parent.lastchild == fire) {
				fire.parent.firstchild = null;
				fire.parent.lastchild = null;
			} else if (fire.parent.firstchild == fire && fire.parent.lastchild != fire) {
				fire.parent.firstchild = fire.nextsibling;
				fire.nextsibling.prevsibling = null;
			} else if (fire.parent.lastchild == fire && fire.parent.firstchild != fire) {
				fire.parent.lastchild = fire.prevsibling;
				fire.prevsibling.nextsibling = null;
			} else {
				fire.prevsibling.nextsibling = fire.nextsibling;
				fire.nextsibling.prevsibling = fire.prevsibling;
			}

			DeleteinAVL(root2.firstchild, fire);
		} else
			throw new IllegalIDException("E");
	}

	public int boss(int id) throws IllegalIDException {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		Node N = search(root2.firstchild, id);
		if (N == null) {
			throw new IllegalIDException("E");
		}
		if (N.parent == null) {
			return -1;
		} else
			return N.parent.id;

	}

	public int lowestCommonBoss(int id1, int id2) throws IllegalIDException {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		Node N1 = search(root2.firstchild, id1);
		Node N2 = search(root2.firstchild, id2);
		if (N1 == null || N2 == null) {
			throw new IllegalIDException("E");
		}
		if (N1 == root1.firstchild || N2 == root1.firstchild) {
			return -1;
		}
		while (N1.level > N2.level) {
			N1 = N1.parent;
		}
		while (N2.level > N1.level) {
			N2 = N2.parent;
		}
		N1 = N1.parent;
		N2 = N2.parent;
		while (N1 != N2) {
			N1 = N1.parent;
			N2 = N2.parent;
		}
		return N1.id;
	}

	String getlevel(Node root, int leveled, int initial_level, int initial_id) {
		String s1 = "";
		if (root == null) {
			return "";
		}
		s1 += getlevel(root.left, leveled, initial_level, initial_id);
		if (s1.length() != 0)
			s1 += " ";
		if (root.level == leveled) {
			Node temp = root.parent;
			int i = leveled - 1;
			while (i != initial_level) {
				i--;
				temp = temp.parent;
			}
			if (temp.id == initial_id)
			s1 += root.id;
		}
		if (s1.length() != 0)
			s1 += " ";
		s1 += getlevel(root.right, leveled, initial_level, initial_id);
		return s1;
	}

	public String toString(int id) throws IllegalIDException {
		// your implementation
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		Node N = search(root2.firstchild, id);
		if (N == null) {
			throw new IllegalIDException("E");
		}
		String s1 = "";
		s1 += N.id + " ";
		int leveled = N.level + 1;
		int initial_level = N.level;
		int initial_id = id;
		while (true) {
			String s = getlevel(root2.firstchild, leveled, initial_level, initial_id);
			if (s.length() == 0)
				break;
			else if (s.charAt(0) == ' ')
				break;
			else
				s1 += s + " ";
			leveled++;
		}
		for (int i = 0; i < s1.length();) {
			if (s1.charAt(i) == ' ' && s1.charAt(i - 1) == ' ') {
				s1 = s1.substring(0, i) + s1.substring(i + 1);
			} else
				i++;
		}
		return s1;
	}
}
