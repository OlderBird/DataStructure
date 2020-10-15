#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

#define LEN 5;               //ģ�����������Ч�ڵ�ĸ���

using namespace std;
//��������㷨

typedef struct Node
{
    int  data;              //������
    struct Node *next;      //ָ����
}NODE,*PNODE;

PNODE init_list(void);                  //����һ����ѭ�������������������ͷ�ڵ�ĵ�ַ����pHead
void select_list(PNODE);                //����
bool insert_list(PNODE, int, int);      //��
bool delete_list(PNODE, int, int *);    //ɾ
void sort_list(PNODE);                  //����

//=============================================================

int main(void)
{
    PNODE head = NULL;
    head = init_list();

    insert_list(head, 2, 6);

    int val;
    delete_list(head, 4, &val);

    select_list(head);
    return 0;
}

//=============================================================

//���������ǵ�ַ
PNODE init_list(void) {
    int i = 0;
    int length = LEN;

    //����һ��ͷ���(������һ���������Ч���ݵ�ͷ���)
    PNODE head = (PNODE)malloc(sizeof(NODE));

    if (head == NULL) {
        cout<<"����ʧ��"<<endl;
        exit(-1);
    }

    PNODE pTail = head; //��ʱpTail��pHead��ָ��ͷ��㣬Ȼ��ͷ���ָ�������
    pTail->next = NULL;

    for ( i = 0; i < length; i++) {
        //ÿ��ѭ������½ڵ�
        PNODE pNew = (PNODE)malloc(sizeof(NODE));
        if (pNew == NULL) {
            cout<<"����ʧ�ܣ�������ֹ��"<<endl;
            exit(-1);
        }
        //pNew����һ����ʱ�Ľڵ㣬�������ݷŵ������ʱ����
        pNew->data = i + 1;
        //�½ڵ���ҵ����������β���ĺ��棨β�巨��
        //����һ��ָ��pTail��Զָ��β���
        pTail->next = pNew;
        pNew->next = NULL;
        //Ȼ���½�����pNew����β�����
        pTail = pNew;
    }
    return head;
}

void select_list(PNODE head) {
    PNODE p = head->next;
    while (p != NULL) {
        cout<<p->data<<endl;
        p = p->next;
    }


}

bool insert_list(PNODE head, int index, int value) {
    int i = 0;
    PNODE p = head;

    //�� p �ƶ��� index-1��λ��
    while ( p != NULL && i < index-1) {
        p = p->next;
        i++;
    }
    //�ж�����Խ��
    if ( i > index-1 || p == NULL)
        return false;

    //�����µĽڵ�
    //�ȷ��ڴ�
    PNODE pNew = (PNODE)malloc(sizeof(NODE));
    if( pNew == NULL) {
        cout<<"��̬�ڴ����ʧ��"<<endl;
        exit(-1);
    }
    PNODE q = p->next;

    pNew->data = value;
    p->next = pNew;
    pNew->next = q;

    return true;
}

bool delete_list(PNODE head, int index, int *pVal) {
        int i = 0;
    PNODE p = head;

    //�� p �ƶ��� index-1��λ��
    while ( p->next != NULL && i < index-1) {
        p = p->next;
        i++;
    }
    //�ж�����Խ��
    if ( i > index-1 || p->next == NULL)
        return false;

    //�ҵ�Ҫɾ������
    PNODE q = p->next;
    //ɾ֮ǰ�����ݱ��浽һ���ط�
    *pVal = q->data;

    //
    p->next = p->next->next;
    free(q);
    q = NULL;   //����Ұָ�룬���ָ������

    return true;
}

void sort_list(PNODE head) {
    int i, j, t, length=LEN;
    //����
    PNODE p = head->next, q;
    //ѡ������
    for ( i = 0; i < length - 1 ; i++, p = p->next ) {
        for ( j = i+1, q = p->next; j < length; j++,q = q->next ) {
            if ( p->data < q->data ) {
                t = p->data;
                p->data = q->data;
                q->data = t;
            }
        }
    }
}
