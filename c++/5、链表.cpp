#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

#define LEN 5;               //模拟用来存放有效节点的个数

using namespace std;
//链表基本算法

typedef struct Node
{
    int  data;              //数据域
    struct Node *next;      //指针域
}NODE,*PNODE;

PNODE init_list(void);                  //创建一个非循环单链表，并将该链表的头节点的地址赋给pHead
void select_list(PNODE);                //遍历
bool insert_list(PNODE, int, int);      //增
bool delete_list(PNODE, int, int *);    //删
void sort_list(PNODE);                  //排序

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

//返回类型是地址
PNODE init_list(void) {
    int i = 0;
    int length = LEN;

    //生成一个头结点(分配了一个不存放有效数据的头结点)
    PNODE head = (PNODE)malloc(sizeof(NODE));

    if (head == NULL) {
        cout<<"分配失败"<<endl;
        exit(-1);
    }

    PNODE pTail = head; //此时pTail和pHead都指向头结点，然把头结点指针域清空
    pTail->next = NULL;

    for ( i = 0; i < length; i++) {
        //每次循环造出新节点
        PNODE pNew = (PNODE)malloc(sizeof(NODE));
        if (pNew == NULL) {
            cout<<"分配失败，程序终止！"<<endl;
            exit(-1);
        }
        //pNew生成一个临时的节点，并把数据放到这个临时域中
        pNew->data = i + 1;
        //新节点需挂到整个链表的尾结点的后面（尾插法）
        //定义一个指针pTail永远指向尾结点
        pTail->next = pNew;
        pNew->next = NULL;
        //然后新进来的pNew就是尾结点了
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

    //将 p 移动到 index-1的位置
    while ( p != NULL && i < index-1) {
        p = p->next;
        i++;
    }
    //判断数组越界
    if ( i > index-1 || p == NULL)
        return false;

    //放入新的节点
    //先分内存
    PNODE pNew = (PNODE)malloc(sizeof(NODE));
    if( pNew == NULL) {
        cout<<"动态内存分配失败"<<endl;
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

    //将 p 移动到 index-1的位置
    while ( p->next != NULL && i < index-1) {
        p = p->next;
        i++;
    }
    //判断数组越界
    if ( i > index-1 || p->next == NULL)
        return false;

    //找到要删的数据
    PNODE q = p->next;
    //删之前将数据保存到一个地方
    *pVal = q->data;

    //
    p->next = p->next->next;
    free(q);
    q = NULL;   //避免野指针，清空指针内容

    return true;
}

void sort_list(PNODE head) {
    int i, j, t, length=LEN;
    //重载
    PNODE p = head->next, q;
    //选择排序
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
