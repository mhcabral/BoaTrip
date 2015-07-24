<?php

namespace app\controllers;

use Yii;
use app\models\Barco;
use app\models\BarcoSearch;
use app\models\UploadForm;
use app\models\PermissionHelpers;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\web\UploadedFile;

/**
 * BarcoController implements the CRUD actions for Barco model.
 */
class BarcoController extends Controller {
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
     * Lists all Barco models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new BarcoSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Barco model.
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
     * Creates a new Barco model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Barco();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Barco model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param string $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
        	$model->imageFile = UploadedFile::getInstance($model, 'imageFile');
        	$model->upload();
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Barco model.
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
     * Finds the Barco model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param string $id
     * @return Barco the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Barco::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
    
    public function actionMobileIndex()
    {
    	\Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    	$models = Barco::find()
    	->asArray()
    	->all();
    
    	$totalItems=count($models);
    
    	return array('status'=>1,'data'=>$models,'totalItems'=>$totalItems);
    
    }
    
}
