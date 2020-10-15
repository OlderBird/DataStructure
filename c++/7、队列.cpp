#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

#define MAX 100

using namespace std;
//ѭ������

typedef struct Queue
{
    int *data;
    int front;  //����ͷԪ��
    int rear;   //����βԪ�ص���һ��Ԫ��
}QUEUE;

void init(QUEUE *);            //ѭ�����г�ʼ��
bool int_queue(QUEUE &, int);       //���
bool out_queue(QUEUE &, int *);       //����
void select_queue(QUEUE *);    //����

//=============================================================
int main(void)
{
    QUEUE Q;
    int value;

    init(&Q);
    int_queue(Q,1);
    int_queue(Q,2);
    int_queue(Q,3);
    int_queue(Q,4);
    int_queue(Q,5);
    select_queue(&Q);

    out_queue(Q,&value);
    cout<<value<<endl;
    select_queue(&Q);

    out_queue(Q,&value);
    select_queue(&Q);

    return 0;
}

//=============================================================


void init(QUEUE *q) {
    q->data = (int *)malloc(sizeof(int)*MAX);
    if (!q->data) {
            cout<<"���г�ʼ��ʧ�ܣ�"<<endl;
            exit(-1);
    }

    q->front = q->rear = 0;
}

bool int_queue(QUEUE &q, int value) {
    if ((q.rear + 1)%MAX == q.front) {
        cout<<"����������"<<endl;
        return false;
    }

    q.data[q.rear] = value; //����������
    q.rear = (q.rear+1)%MAX;
    return true;
}

bool out_queue(QUEUE &q, int *value) {
    if(q.front == q.rear) {
        cout<<"����Ϊ�գ�"<<endl;
        return false;
    }

    *value = q.data[q.front];
    q.front = (q.front + 1)%MAX;
    return true;
}

void select_queue(QUEUE *q) {
    if(q->front == q->rear) {
        cout<<"����Ϊ�գ�"<<endl;
        return;
    }

/*  ��д�����󣬲���ֱ����frontѭ�������ƻ����нṹ
    front��·�ƣ����ܴ���·����
    while( q->front != q->rear) {
        cout<<q->data[q->front];
        q->front = (q->front+1)%MAX;
    }
*/
    int i = q->front;
    while( i != q->rear) {
        cout<<q->data[i];
        i = (i+1)%MAX;
    }
    cout<<""<<endl;
}

