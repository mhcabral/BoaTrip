<?php

namespace app\controllers;

use Yii;
use app\models\Localidade;
use app\models\LocalidadeSearch;
use app\models\PermissionHelpers;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\helpers\ArrayHelper;

/**
 * LocalidadeController implements the CRUD actions for Localidade model.
 */
class LocalidadeController extends Controller {
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
						'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['post'],
                ],
            ],
        ];
    }

    /**
     * Lists all Localidade models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new LocalidadeSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Localidade model.
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
     * Creates a new Localidade model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Localidade();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Localidade model.
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
     * Deletes an existing Localidade model.
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
     * Finds the Localidade model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param string $id
     * @return Localidade the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Localidade::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
    
    public function actionMobileIndex()
    {
    	\Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    	//return [
    	//		'message' => 'hello world',
    	//		'code' => 100,
    	//];
    	//$models = $command->queryAll();
    	$models = \app\models\Localidade::find()
    		->with(['uf'])
    		->asArray()
    		->all();
    
    	$totalItems=count($models);
    
    	//$this->setHeader(200);
    
    	//echo json_encode(array('status'=>1,'data'=>$models,'totalItems'=>$totalItems),JSON_PRETTY_PRINT);
    	return array('status'=>1,'data'=>$models,'totalItems'=>$totalItems);
    
    }
}
