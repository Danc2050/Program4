public class Tree extends Utility {
    protected BST root;//Holds onto our root for removal.

    //This is used to traverse through and find the node...
    protected BST remove(BST root, String key){
        if(root == null)
            return root;
        int cmp = key.compareTo(root.keyword);

        if(root.keyword.contains(key))
        {
            root = delete(root);
        }
        if(cmp < 0)
        {
            root.connectLeft(remove(root.goLeft(), key)); return root;
        }
        if(cmp > 0)
        {
            root.connectRight(remove(root.goRight(), key)); return root;
        }

    return root;
    }

    //Once our node is found, we call delete and return the next node to take the place of the old.
    protected BST delete(BST root){
        BST temp1 = root.goLeft();//Testing for a left child.
        BST temp2 = root.goRight();//Testing for a right child

        if(temp1 == null && temp2 == null)//A leaf...no children.
        {
            root = null;//set the root that we wanted deleted to null.
            return root;
        }

        if(temp1 != null && temp2 == null)//One left child...
        {
            return temp1;//Return to connect.
        }

        if(temp1 == null && temp2 != null)//One right child...
        {
            return temp2;//Return to connect.
        }

        if(temp1 != null && temp2 != null)//The IOS case...
        {
            BST hold = root.goLeft();
            root = IOS(root.goRight());
            root.left = hold; return root;
        }
        return null;
    }

    //Finds the IOS and returns it to root, setting it to null.
   protected BST IOS(BST current)//Function is called with root.goRight() so traverse left...
   {
        BST temp1 = current.goLeft();//Get the left reference.
       if(temp1 == null)//Current is the IOS since current's left == null;
            return current;//Return IOS.
       BST temp2 = temp1.goLeft();//We are looking ahead twice in this case. If temp1.goLeft() is null, then we need to
                                  //return temp1;
       if(temp2 == null)
       {
            return temp1;
       }
       IOS(current.goLeft());
       return current;
   }

    public Tree(BST ref){//Allocates our root pointer to point to the root of our tree (of type BST class).
        this.root = ref;
    }
}
