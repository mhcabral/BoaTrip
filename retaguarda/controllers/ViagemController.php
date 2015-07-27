<?php

namespace app\controllers;

use Yii;
use app\models\Viagem;
use app\models\PassagemSearch;
use app\models\ViagemSearch;
use app\models\PermissionHelpers;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\base\Object;

/**
 * ViagemController implements the CRUD actions for Viagem model.
 */
class ViagemController extends Controller
{
	public function behaviors() {
		return [ 
				'access' => [ 
						'class' => \yii\filters\AccessControl::className (),
						'only' => [ 
								'index',
								'view',
								'create',
								'update',
								'delete' 
						],
						'rules' => [ 
								[ 
										'actions' => [ 
												'index',
												'create',
												'view' 
										],
										'allow' => true,
										'roles' => [ 
												'@' 
										],
										'matchCallback' => function ($rule, $action) {
											return PermissionHelpers::requireMinimumRole ( 'Admin' ) && PermissionHelpers::requireStatus ( 'Ativo' );
										} 
								],
								[ 
										'actions' => [ 
												'update',
												'delete' 
										],
										'allow' => true,
										'roles' => [ 
												'@' 
										],
										'matchCallback' => function ($rule, $action) {
											return PermissionHelpers::requireMinimumRole ( 'Admin' ) && PermissionHelpers::requireStatus ( 'Ativo' );
										} 
								] 
						] 
				],
				'verbs' => [ 
						'class' => VerbFilter::className (),
						'actions' => [ 
								'delete' => [ 
										'post' 
								] 
						] 
				] 
		];
	}

    /**
     * Lists all Viagem models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new ViagemSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Viagem model.
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
     * Creates a new Viagem model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Viagem();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Viagem model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param string $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);
        $searchModel = new PassagemSearch();
        $searchModel->viagem_id = $id; // filtra a passagem para somente a viagem selecionada
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);
        

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
        	$teste = 'Teste';
            return $this->render('update', array(
                'model' => $model,
            	'searchModel' => $searchModel,
            	'dataProvider' => $dataProvider,
            ));
        }
    }

    /**
     * Deletes an existing Viagem model.
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
     * Finds the Viagem model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param string $id
     * @return Viagem the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Viagem::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
    
    public function actionMobileFind($l1,$l2,$ma)
    {
    	$meses = array('Janeiro' => 1, 'Fevereiro' => 2, 'Março' => 3, 'Abril' => 4,
    			'Maio' => 5, 'Junho' => 6, 'Julho' => 7, 'Agosto' => 8,
    			'Setembro' => 9, 'Outubro' => 10, 'Novembro' => 11, 'Dezembro' => 12);
    	$mes = $meses[substr($ma, 0, -4)];
    	$ano = substr($ma, -4, 4);
    	Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    	$models = Viagem::find()
    		->innerJoinWith(['passagems'])
    		->andFilterWhere(['localidade_origem' => $l1, 'localidade_destino' => $l2])
    		->andFilterWhere(['=','MONTH(data_saida)',$mes])
    		->asArray()->all();
    
    	$totalItems=count($models);
    	return array('status'=>1,'data'=>$models,'totalItems'=>$totalItems);
    
    }
    
    public function actionMobilePromocao()
    {
    	Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    	$models = Viagem::find()
    	->innerJoinWith([
    			'passagems'=> function ($query) {
    				$query->andWhere('valor_desconto > 0');
    			},
    	])
    	->asArray()->all();
    
    	$totalItems=count($models);
    	return array('status'=>1,'data'=>$models,'totalItems'=>$totalItems);
    
    }
    
    
}
