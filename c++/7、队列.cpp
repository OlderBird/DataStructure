#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

#define MAX 100

using namespace std;
//循环队列

typedef struct Queue
{
    int *data;
    int front;  //队列头元素
    int rear;   //队列尾元素的下一个元素
}QUEUE;

void init(QUEUE *);            //循环队列初始化
bool int_queue(QUEUE &, int);       //入队
bool out_queue(QUEUE &, int *);       //出队
void select_queue(QUEUE *);    //遍历

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
            cout<<"队列初始化失败！"<<endl;
            exit(-1);
    }

    q->front = q->rear = 0;
}

bool int_queue(QUEUE &q, int value) {
    if ((q.rear + 1)%MAX == q.front) {
        cout<<"队列已满！"<<endl;
        return false;
    }

    q.data[q.rear] = value; //类似于数组
    q.rear = (q.rear+1)%MAX;
    return true;
}

bool out_queue(QUEUE &q, int *value) {
    if(q.front == q.rear) {
        cout<<"队列为空！"<<endl;
        return false;
    }

    *value = q.data[q.front];
    q.front = (q.front + 1)%MAX;
    return true;
}

void select_queue(QUEUE *q) {
    if(q->front == q->rear) {
        cout<<"队列为空！"<<endl;
        return;
    }

/*  此写法错误，不能直接用front循环，会破坏队列结构
    front是路牌，不能带着路牌走
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

