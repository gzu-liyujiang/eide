package aenu.gradle;
import java.util.List;
import aenu.gradle.expr.Expression;
import java.util.ArrayList;

public class G_Tree{
    
    public static class Node{
        final String name;
        final List<Expression> values=new ArrayList<>();
        final List<Node> childNodes=new ArrayList<>();
        public Node(String name){
            this.name=name;
        }
        
        public List<Expression> values(){
            return values;
        }
        
        public Node getChlidNode(String name){
            String[] ns=name.split("\\.");

            List<Node> nodes=this.childNodes;
            boolean find;
            for(int i=0;i<ns.length-1;i++){
                find=false;
                for(Node node:nodes){
                    if(node.name.equals(ns[i])){
                        nodes=node.listChildNode();
                        find=true;
                        break;
                    }
                }
                if(!find)
                    return null;
            }

            for(Node node:nodes){
                if(node.name.equals(ns[ns.length-1])){
                    return node;
                }
            }

            return null;
        }
        
        void addChildNode(Node node){
            childNodes.add(node);
        }
        
        public List<Node> listChildNode(){
            return childNodes;
        }
    }
    
    final List<Node> nodes=new ArrayList<>();
    
    void addNode(Node node){
        nodes.add(node);
    }
    
    public List<Node> listNode(){
        return nodes;
    }
    
    public Node getNode(String names){
        String[] ns=names.split("\\.");
        
        List<Node> nodes=this.nodes;
        boolean find;
        for(int i=0;i<ns.length-1;i++){
            find=false;
            for(Node node:nodes){
                if(node.name.equals(ns[i])){
                    nodes=node.listChildNode();
                    find=true;
                    break;
                }
            }
            if(!find)
                return null;
        }
        
        for(Node node:nodes){
            if(node.name.equals(ns[ns.length-1])){
                return node;
            }
        }
        
        return null;
    }
}
