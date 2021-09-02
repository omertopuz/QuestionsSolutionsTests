package net.questions.solutions;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return val == ((ListNode) o).val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode temp = this;
        while (temp != null){
            sb.append(temp.val);
            temp = temp.next;
        }
        return sb.toString();
    }
}