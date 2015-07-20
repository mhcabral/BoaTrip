<?php

namespace app\controllers;

use Yii;
use app\models\BarcoFoto;
use app\models\BarcoFotoSearch;
use app\models\PermissionHelpers;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use app\models\Barco;

/**
 * BarcoFotoController implements the CRUD actions for BarcoFoto model.
 */
class BarcoFotoController extends Controller {
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
     * Lists all BarcoFoto models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new BarcoFotoSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single BarcoFoto model.
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
     * Creates a new BarcoFoto model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new BarcoFoto();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing BarcoFoto model.
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
     * Deletes an existing BarcoFoto model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param string $id
     * @return mixed
     */
    public function actionDelete($id)
    {
        $model = $this->findModel($id);
        $barco = $model->barco;
       	$image = $model->foto_url;
       	
       	$model->delete();
       	
       	unlink($image);

        //return $this->redirect(['index']);
       	return $this->redirect(['barco/update', 'id'=>$barco->id]);
    }

    /**
     * Finds the BarcoFoto model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param string $id
     * @return BarcoFoto the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = BarcoFoto::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
}
