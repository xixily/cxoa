/**
 * Created by Administrator on 2016/10/13.
 */
/**
 * ˳�Ṥ��
 * @type {{sendOrder: Function, queryOrder: Function, queryRouteByid: Function, queryRouteByMailno: Function, queryRoute: Function}}
 */
var SFUtil = {
    /**
     *���Ϳ�ݣ���ȡ�˵���
     * @params order ��������
     * //Order ����
     orderid;//(����)
     mailno;//˳���˵���
     is_gen_bill_no;//Ҫ�󷵻��˵���
     j_company;//�ļ�����λ
     j_contact;//�ļ�����ϵ��
     j_tel;//�ļ�����ϵ�绰
     //	j_shippercode;//�ļ�������/���д���(�������羳����)
     //	j_country;//�ķ�����
     j_province;//�ļ�������ʡ��
     j_city;//�ļ������ڳ�������
     j_county;//�ļ���������/��
     j_address;//�ļ�����ϸ��ַ(�����������Ҫ ���ɵ����˵�)
     //	j_post_code ;//�ķ��ʱ�(�������羳����)
     //Order ����
     d_company;//������˾���ƣ����
     d_contact;//��������ϵ�ˣ����
     d_tel;//��������ϵ�绰�����
     d_mobile;//�������ֻ�
     //	d_deliverycode;//���������룬���������羳���
     //	d_country;//��������
     d_province;//����������ʡ��
     d_city;//���������ڳ�����
     d_county;//������������/��
     d_address;//��������ϸ��ַ���������������� d_province/d_city �ֶΣ�����ϸ��ַ �����ʡ����Ϣ������ߵ�ַʶ���
     d_post_code;//�����ʱ�
     //Order ������Ϣ
     custid;//˳���½Ῠ�ţ�������
     pay_method;//���ʽ��1���ķ�����2���շ�����3����������
     express_type;//�����Ʒ��𣬼��������Ʒ������1����׼��ݣ�2��˳���ػݣ�3�������ػݣ�5��˳��γ���6�����ռ���7����������;28������ר��
     parcel_quantity;//������,��ĸ��
    cargo_length;//�ͻ����������ܳ�number(10,3)
    cargo_width;//�ͻ����������ܿ�number(10,3)
    cargo_height;//�ͻ�����������number(10,3)
    volume;//�������������number(17,3)
    cargo_total_weight ;//��������������number(10,3)
     //	private Float declared_value;//�ͻ�����������������ֵ(�羳�����
     //	declared_value_currency;//����������ֵ�ұ�CNY: ����� ��USD: ��Ԫ ��HKD: �۱� ��NTD: ��̨�� ��
     //	customs_batchs;//��������
     sendstarttime;//Ҫ������ȡ����ʼʱ��
     is_docall;//�Ƿ�Ҫ��ͨ���Ƿ��ֳ��ն�֪ͨ˳��
     //	return_tracking;//˳��ǩ�ص������˵���
     //	d_tax_no;//�ռ���˰��
     tax_pay_type;//˰�𸶿ʽ�� 1���ķ���2������
     //	tax_set_accounts;//˰������˺�
     //	original_number;//����ԭʼ������
     payment_tool;//֧������
     payment_number;//֧������
     //	goods_code;//��Ʒ���
     //	in_process_waybill_no;//ͷ���˵���
     //	brand;//����Ʒ��
     //	specifications;//�������ͺ�
     //	temp_range;//�¶ȷ�Χ���ͣ����������� express_type Ϊ 12��1����أ�3������ ��
     order_name;//�ͻ������µ�������
     //	order_cert_type;//�ͻ������µ���֤������
     //	order_source;//�ͻ�������Դ
     //	template;//ҵ��ģ�����
     remark;//��ע
     oneself_pickup_flg;//�����ȡ��1 ��ʾ�ͻ�ͬ������ȡ�� �� 1 ��ʾ�ͻ���ͬ������ȡ
     //	dispatch_sys;//�������ݷַ���ϵͳ����
     *@param cargos [{}] ������Ϣ
     * name;//��������(������ɵ����˵�����)
    * count;//�����������������羳���
    * unit;//���ﵥλ���������羳���
    * private Float weight;//�������ﵥλ�������������羳���
    * amount;//���ﵥ�ۣ��������羳���
    * currency;//���ﵥ�۵ıұ�
    * source_area;//ԭ���ع����������羳���
    * product_record_no;//�����Ʒ���챸�����
    * good_prepard_no;//��Ʒ���ر�����
     * @param addService
     * name;//��ֵ���������� COD �ȡ�
     *value;//��ֵ������չ���ԣ��ο���ֵ���� ֵ˵����
     *value1;//��ֵ������չ����
     */
    sendOrder : function(order, cargos, addService){

    },
    printKuaidiList : function(){

    },
    queryOrder : function(){

    },
    queryRouteByid : function(){

    },
    queryRouteByMailno : function(){

    },
    /**
     * ·�ɲ�ѯ
     * @param rtRequest {type:,orderid:,method_type:}
     * @type {type:'1����˳���˵��Ų�ѯ��2���ͻ������Ų�ѯ',orderid:'����',method_type:'·�ɲ�ѯ���1����׼·�ɲ�ѯ ;2������·�ɲ�ѯ'}
     * @param  type 1����˳���˵��Ų�ѯ��2���ͻ������Ų�ѯ
     * @param orderid ����
     * @param method_type ·�ɲ�ѯ���1����׼·�ɲ�ѯ ;2������·�ɲ�ѯ
     */
    queryRoute : function(rtRequest){

    }

}