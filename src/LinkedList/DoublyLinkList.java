package LinkedList;

import Entity.Book;

public class DoublyLinkList {
    private Link first;
    private Link last;

    public DoublyLinkList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first==null;
    }

    public void insertFirst(Book e) {
        Link newLink = new Link(e);
        if(isEmpty()) last = newLink;
        else first.prev = newLink;
        newLink.next = first;
        first = newLink;
    }

    public void insertNOrder(Book e) {  //The algo is like insertAfter but after the right alphabetical order

        //The to be inserted book
        Link newLink = new Link(e);
        String title = newLink.getElement().getTitle();

        if(isEmpty()) {     //If the list is empty
            insertFirst(e);
            return;
        }

        //Store the first link and get its title string value
        Link current = first;
        String tempStr = current.getElement().getTitle();

        //Iterate until the compare return 0 or 1
        while(current!=null && title.compareToIgnoreCase(tempStr)>0) {      //Comparres the tempStr to title first if the title not over the alpabeta
            current = current.next;
            if(current!=null) tempStr = current.getElement().getTitle();
        }

        //Insert after the templink
        if(current==null) {     //Reach the end of the list
          insertLast(e);
        } else if(current.prev==null){   //if current is at the start of list
            insertFirst(e);
        }else { //if current is at between the list
            current = current.prev;
            newLink.next = current.next;    //newLink -> old next
            current.next.prev = newLink;    //newLink <- old next
            newLink.prev = current;         //old current <- newLink
            current.next = newLink;         //old current -> newLink
        }
        //displayBook();
    }

    public void insertLast(Book e) {
        Link newLink = new Link(e);
        if(isEmpty()) first = newLink;
        else {
            last.next = newLink;
            newLink.prev = last;
        }
        last = newLink;
    }

    public Link remove(Link e) {
        Link current = first;
        //while(e.get)
        return null;
    }

    public Link removeFirst() {
        Link temp = first;
        first = first.getNext();
        return temp;
    }

    public void displayBook() {
        Link current = first;
        while(current!=null) {
            System.out.print(current.getElement().getTitle() + " | ");
            current = current.next;
        }
        System.out.println("");
    }

    public Link getFirst(){
        return first;
    }
    public Link getLast(){
        return last;
    }
}
