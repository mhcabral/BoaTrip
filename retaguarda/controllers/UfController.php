<?php

namespace app\controllers;

use Yii;
use app\models\Uf;
use app\models\UfSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * UfController implements the CRUD actions for Uf model.
 */
class UfController extends Controller
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
     * Lists all Uf models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new UfSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Uf model.
     * @param integer $id
     * @param string $uf_nome
     * @return mixed
     */
    public function actionView($id, $uf_nome)
    {
        return $this->render('view', [
            'model' => $this->findModel($id, $uf_nome),
        ]);
    }

    /**
     * Creates a new Uf model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Uf();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id, 'uf_nome' => $model->uf_nome]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Uf model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @param string $uf_nome
     * @return mixed
     */
    public function actionUpdate($id, $uf_nome)
    {
        $model = $this->findModel($id, $uf_nome);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id, 'uf_nome' => $model->uf_nome]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Uf model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @param string $uf_nome
     * @return mixed
     */
    public function actionDelete($id, $uf_nome)
    {
        $this->findModel($id, $uf_nome)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the Uf model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @param string $uf_nome
     * @return Uf the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id, $uf_nome)
    {
        if (($model = Uf::findOne(['id' => $id, 'uf_nome' => $uf_nome])) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
}
