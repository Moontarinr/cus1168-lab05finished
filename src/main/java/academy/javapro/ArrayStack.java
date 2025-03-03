package academy.javapro;

/**
 * Array-based implementation of the CustomStack interface.
 * @param <T> the type of elements in the stack
 */
public class ArrayStack<T extends Number> implements CustomStack<T> {
    // Constants for stack configuration
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    // Static variables for tracking across all instances
    private static int totalStacks = 0;
    private static int totalElements = 0;

    // Instance variables
    private Object[] elements;  // Using Object[] since generic arrays aren't directly supported
    private int top;  // Index of the top element
    private int operationCount;  // Tracks operations on this stack
    private final int stackId;  // Unique identifier for this stack instance

    /**
     * Creates a new ArrayStack with default capacity.
     */
    public ArrayStack() {
   
    elements = new Object[DEFAULT_CAPACITY];

    top = -1;

    operationCount = 0;

    
    stackId = ++totalStacks;
    }

    @Override
    public void push(T element) {
        
    operationCount++;

        
    if (top == elements.length - 1) {
            resize();
        }

        
    elements[++top] = element;

        
    totalElements++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        
    operationCount++;

    if (isEmpty()) {
            return null;
        }
     
    T element = (T) elements[top];

    elements[top] = null;

    top--;
    totalElements--;

    return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {

     operationCount++;

     if (isEmpty()) {
            return null;
        }

    return (T) elements[top];
    }

    @Override
    public boolean isEmpty() {
        
    operationCount++;

    
        return top == -1;
    }

    @Override
    public int size() {
    
    operationCount++;

    return top + 1;
   
   }

    private void resize() {

    int newCapacity = (int) (elements.length * GROWTH_FACTOR);

    Object[] newElements = new Object[newCapacity];

    System.arraycopy(elements, 0, newElements, 0, elements.length);
       
    elements = newElements;
    }

    public void addTopTwo() {
    if (size() < 2) {
            throw new IllegalStateException("Not enough elements to add");
        }

        T first = pop();
        T second = pop();

        Number result;

        if (first instanceof Integer && second instanceof Integer) {
            result = first.intValue() + second.intValue();
        } else {
            result = first.doubleValue() + second.doubleValue();
        }


        if (result instanceof Integer) {
            push((T) Integer.valueOf(result.intValue()));
        } else {
            
            push((T) Double.valueOf(result.doubleValue()));
        }
    }

    public String getStats() {
        return "Stack #" + stackId + ": Size=" + size() + ", Operations=" + operationCount;
    }

    public static String getGlobalStats() {
        return "Total stacks: " + totalStacks + ", Total elements: " + totalElements;
    }
}
