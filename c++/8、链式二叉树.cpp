#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

using namespace std;
//链式二叉树

typedef struct Tree
{
    char data;
    struct Tree *LChild;    //左孩子
    struct Tree *RChild;    //右孩子
}TREE,*PTREE;


PTREE createTree(void);     //创建二叉树
void preTraverse(PTREE);    //前序遍历
void inTraverse(PTREE);     //中序遍历
void postTraverse(PTREE);   //后序遍历

//=============================================================
int main(void)
{
    PTREE t = createTree();

    preTraverse(t);
    cout<<endl;
    inTraverse(t);
    cout<<endl;
    postTraverse(t);

    return 0;

    return 0;
}

//=============================================================

//返回值是根节点的地址
PTREE createTree()
{
    PTREE A = (PTREE)malloc(sizeof(TREE)); A->data = 'A';
    PTREE B = (PTREE)malloc(sizeof(TREE)); B->data = 'B';
    PTREE C = (PTREE)malloc(sizeof(TREE)); C->data = 'C';
    PTREE D = (PTREE)malloc(sizeof(TREE)); D->data = 'D';
    PTREE E = (PTREE)malloc(sizeof(TREE)); E->data = 'E';

    A->LChild = B;
    A->RChild = C;
    B->LChild = B->RChild = NULL;
    C->LChild = D;
    C->RChild = NULL;
    D->LChild = NULL;
    D->RChild = E;
    E->LChild = E->RChild = NULL;

    return A;
}

void preTraverse(PTREE p) {
    if (p != NULL) {
        //p->LChild可以代表整个左子树
        //先访问根节点
        cout<<p->data;
        //再先序访问左子数
        preTraverse(p->LChild);
        //再先序访问右子树
        preTraverse(p->RChild);
    }
}

void inTraverse(PTREE p) {
    if (p != NULL) {
        //先中序访问左子数
        preTraverse(p->LChild);
        //再访问根节点
        cout<<p->data;
        //再中序访问右子树
        preTraverse(p->RChild);
    }
}

void postTraverse(PTREE p) {
    if (p != NULL) {
        //先后序访问左子数
        preTraverse(p->LChild);
        //再后序访问右子树
        preTraverse(p->RChild);
        //再访问根节点
        cout<<p->data;

    }
}


