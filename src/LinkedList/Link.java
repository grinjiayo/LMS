package LinkedList;

import Entity.Book;
public class Link {  //This link is for doublyLinkedList
    private Book element;
    protected Link prev;
    protected Link next;

    protected Link(Book b) {
        element = b;
    }

    public Book getElement() {return element;}
    public void setElement(Book element) {this.element = element;}
    public Link getPrev() {return prev;}
    public Link getNext() {return next;}
    public void setPrev(Link p) {prev = p;}
    public void setNext(Link n) {next = n;}
}
