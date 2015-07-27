<?php

namespace app\controllers;

use Yii;
use app\models\Venda;
use app\models\VendaSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use app\models\User;
use app\models\Passagem;

/**
 * VendaController implements the CRUD actions for Venda model.
 */
class VendaController extends Controller
{
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['post'],
                ],
            ],
        ];
    }

    /**
     * Lists all Venda models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new VendaSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Venda model.
     * @param string $id
     * @return mixed
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Creates a new Venda model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate($passagemid)
    {
    	$usuario = User::findOne(Yii::$app->user->id);
    	$passagem = Passagem::findOne($passagemid);
    	date_default_timezone_set("America/Manaus");
        $model = new Venda();
       
        $model->data = date('Y-m-d H:i:s', time());
        $model->passagem_id = $passagem->id;
        if ($passagem->valor_desconto > 0) {
        	$model->valor = $passagem->valor_desconto;
    	} else {$model->valor = $passagem->valor;}
        $model->profile_id = $usuario->profile->id;
        $model->venda_status_id = 3;

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Venda model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param string $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Venda model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param string $id
     * @return mixed
     */
    public function actionDelete($id)
    {
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the Venda model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param string $id
     * @return Venda the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Venda::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
    
    public function actionMobileFind($id)
    {	
    	Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    	$usuario = User::findOne($id);
    	if ($usuario !== null) {
    		$profile = $usuario->profile;
    		if ($profile !== null) {
    			$profile_id = $usuario->profile->id;
    		} else $profile_id = 0;
	    	$models = Venda::find()
	    	->innerJoinWith(['passagem', 'profile', 'vendaStatus'])
	    	->andFilterWhere(['profile_id' => $profile_id])
	    	->asArray()->all();
	    
	    	$totalItems=count($models);
	    	return array('status'=>1,'data'=>$models,'totalItems'=>$totalItems);
    	} else {
    		return array('status'=>0,'data'=>[],'totalItems'=>0);
    	}
    
    }
    
    public function actionMobileCreate()
    {
    	Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    
    	$params=$_REQUEST;
    	$valor = $_REQUEST['valor'];
    	$cartao_numero = $_REQUEST['cartao_numero'];
    	$validade = $_REQUEST['validade'];
    	$user_id  = $_REQUEST['user_id'];
    	$passagem_id  = $_REQUEST['passagem_id'];
    
    	if (($user_id !== null) && ($passagem_id !== null)) {
    		$user = User::findOne($id);
    		if ($user !== null) {
    			$profile = $user->profile;
    			if ($profile == null) return array('status'=> 4, 'data'=>''); //Não tem profile
    			$model = new Venda();
    			 
    			$model->data = date('Y-m-d H:i:s', time());
    			$model->valor = $valor;
    			$model->cartao_numero = $cartao_numero;
    			$model->validade = $validade;
    			$model->profile_id = $profile->id;
    			$model->venda_status_id = 3;
    			if ($model->validate()) {
    				$user = $model->save();
    				return array('status'=> 1, 'data'=>$model); //Ok
    			} else {
    				return array('status'=> 2, 'data'=>''); //Não validou
    			}
    		} else return array('status'=> 0, 'data'=>''); //Não encontrou usuario
    	}
    	return array('status'=> 3, 'data'=>''); //Campos com nulo
    }
}
