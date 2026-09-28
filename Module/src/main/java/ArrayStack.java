// ArrayStack

public class ArrayStack {
    private String[] list;
    private int top;
    public ArrayStack(String[] list, int top){
        this.list = list;
        this.top = top;
    }
    private void grow(){
        String[] bigger = new String[list.length*2];
        System.arraycopy(list, 0, bigger, 0, list.length);
        list = bigger;
    }
    void push(String operand){
        if(top == list.length){
            grow();
        }
        list[top] = operand;
        top++;
    }
    String pop(){
        if(isEmpty()){
            throw new IllegalArgumentException("Stack is empty");
        }
        top--;
        return list[top];
    }
    String peek(){
        if(isEmpty()){
            throw new IllegalArgumentException("Stack is empty");
        }
        return list[top-1];
    }
    int size(){
        return top;
    }
    boolean isEmpty(){
        if(top == 0){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {
        String[] str = new String[5];
        String[] str1 = {"1","2",null, null, null, null};
        ArrayStack s = new ArrayStack(str, 0);
        ArrayStack s1 = new ArrayStack(str1, 2);
        System.out.println(s.isEmpty());
        System.out.println(s1.isEmpty());
        s.push("1");
        System.out.println(s.isEmpty());
        s.push("2");
        s.push("3");
        s.push("4");
        s.push("5");
        s.push("6");
        System.out.println(s.size());
        s1.push("3");
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
    }

}
