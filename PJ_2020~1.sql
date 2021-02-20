CREATE TABLE WRITER(--���� �۾��̶�� �濡�� WRITER �ٸ� ������ U�� �Ǿ������� ���� 
    U_ID NVARCHAR2(20), --���̵�
    U_PW NVARCHAR2(30), --���
    U_NAME NVARCHAR2(20)--������ �г���
);
CREATE TABLE WRITER_CLASS(--�۾��� ���, �⺻���� W�� ������� 
    W_NUM NUMBER,--��ޱ��
    W_C NVARCHAR2(20), --������
    U_ID NVARCHAR2(30) --�������̵�
);
CREATE TABLE BOARD_F(--�����Խ���
    F_NUM NUMBER,--���� ��ȣ
    F_TITLE NVARCHAR2(30),--�� ����
    F_DATE TIMESTAMP DEFAULT SYSDATE,-- �ۼ��� ��¥
    F_CONTENT CLOB, --�۳���
    U_ID NVARCHAR2(20) --�ۼ��� ������ ���̵�
);
CREATE TABLE BOARD_I(--�����Խ���
    I_NUM NUMBER,
    I_TITLE NVARCHAR2(30),--�� ����
    I_DATE TIMESTAMP DEFAULT SYSDATE,
    I_FILE NVARCHAR2(100), --������������
    I_CONTENT CLOB,--��������
    U_ID NVARCHAR2(20)
);
CREATE TABLE BOARD_Q(--�����Խ���
    Q_NUM NUMBER,
    Q_TITLE NVARCHAR2(30),--�� ����
    Q_DATE TIMESTAMP DEFAULT SYSDATE,
    Q_CONTENT CLOB,
    U_ID NVARCHAR2(20)
);
CREATE TABLE BOARD_A(--�����Խ��� �亯
    A_NUM NUMBER,
    A_DATE TIMESTAMP DEFAULT SYSDATE,
    A_CONTENT CLOB,--�亯
    U_ID NVARCHAR2(20),
    Q_NUM NUMBER--�亯�� ��� �����ִ� �����Խ��Ǳۿ� ��ȣ
);
CREATE TABLE BOARD_P(--�����Խ���
    P_NUM NUMBER,
    P_TITLE NVARCHAR2(30),--�� ����
    P_DATE TIMESTAMP DEFAULT SYSDATE,
    P_FILE NVARCHAR2(100), --������������
    U_ID NVARCHAR2(20)
);
--������
CREATE SEQUENCE SEQ_F_NUM INCREMENT BY 1 START WITH 1000;--������ ����, �����Խ���
CREATE SEQUENCE SEQ_P_NUM INCREMENT BY 1 START WITH 1000;--����Խ���
CREATE SEQUENCE SEQ_I_NUM INCREMENT BY 1 START WITH 1000;--�����Խ���
CREATE SEQUENCE SEQ_Q_NUM INCREMENT BY 1 START WITH 1000;--�����Խ���
CREATE SEQUENCE SEQ_A_NUM INCREMENT BY 1 START WITH 1000;--�����Խ��ǿ� �亯
CREATE SEQUENCE SEQ_WC_NUM INCREMENT BY 1 START WITH 1000;--���� ��� �̰��� �ڵ��������Դϴ�.


--������ ���Ƿ� �߰�
INSERT INTO WRITER VALUES('master01', 'master01', '������');
INSERT INTO WRITER VALUES('user02', 'user02pw', '2��');
INSERT INTO WRITER VALUES('user03', 'user03pw', '3����');
INSERT INTO WRITER VALUES('user04', 'user04pw', '���');
INSERT INTO WRITER VALUES('user05', 'user05pw', '���������');

--���Ƿ� ������ �����ڷ� ���� , �ϴ� �����ڸ� MASTER��� ����� ����
INSERT INTO WRITER_CLASS VALUES(SEQ_WC_NUM.NEXTVAL, 'MASTER', 'master01');


