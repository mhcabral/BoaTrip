<?php

namespace app\controllers;

use Yii;
use app\models\User;
use app\models\UserSearch;
use app\models\PermissionHelpers;
use app\models\LoginForm;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use app\models\Profile;
use app\models\SignupForm;
use app\models\Role;
use app\models\Genero;

/**
 * UserController implements the CRUD actions for User model.
 */
class UserController extends Controller
{
    public function behaviors()
    {
        return [
        	'access' => [
        		'class' => \yii\filters\AccessControl::className(),
        		'only' => ['index', 'view','create', 'update', 'delete'],
        		'rules' => [
        			[
        				'actions' => ['index', 'create', 'view',],
        				'allow' => true,
        				'roles' => ['@'],
        				'matchCallback' => function ($rule, $action) {
        					return PermissionHelpers::requireMinimumRole('Admin')
        					&& PermissionHelpers::requireStatus('Ativo');
        				}
        			],
        			[
        				'actions' => [ 'update', 'delete'],
        				'allow' => true,
        				'roles' => ['@'],
        				'matchCallback' => function ($rule, $action) {
        					return PermissionHelpers::requireMinimumRole('Admin')
        					&& PermissionHelpers::requireStatus('Ativo');
        				}
        			],
        		],
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
     * Lists all User models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new UserSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single User model.
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
     * Creates a new User model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new User();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing User model.
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
     * Deletes an existing User model.
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
     * Finds the User model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param string $id
     * @return User the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = User::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
    
    public function actionMobileLogin()
    {
    	Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    	
    	$params=$_REQUEST;
    	$username = $_REQUEST['username'];
    	$password = $_REQUEST['password'];
    	
    	if (($username !== null) && (!empty($password))) {
	    	if (($model1 = User::findByUsername($username)) !== null) {
	    
		    	if ($model1->validatePassword($password)) {
		    		if (($profile = $model1->profile) !== null) {
		    			$model = \app\models\User::find()
		    			->andFilterWhere(['username' => $username])
		    			->with(['profile'])
		    			->asArray()
		    			->all();
		    			return array('status'=> 1, 'data'=> $model);
		    		}
		    		return array('status'=> 2, 'data'=> $model1); // não tem perfil
		    	} else {
		    		return array('status'=> 0, 'data'=>'');
		    	}
	    	}
    	}	
    	return array('status'=> 0, 'data'=>''); //se for nulo
    }
    
    public function actionMobileCreate()
    {
    	Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    	 
    	$params=$_REQUEST;
    	$username = $_REQUEST['username'];
    	$password = $_REQUEST['password'];
    	 
    	if (($username !== null) && (!empty($password))) {
    		if (($model = User::findByUsername($username)) == null) {
    			$model = new SignupForm();
    			
    			$model->username = $username;
    			$model->password = $password;
    			$model->email = $username;
    			if ($model->validate()) {
    				$user = $model->signup();
    				$user->role_id = 1;
    				$user->update();
    				return array('status'=> 1, 'data'=>$user); //Ok
    			} else {
    				return array('status'=> 2, 'data'=>''); //Não validou
    			}
    		} else return array('status'=> 0); //Já Existe
    	}
    	return array('status'=> 3, 'data'=>''); //Campos com nulo
    }
    
    public function actionMobileProfile()
    {
    	Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
    	Yii::$app->response->statusCode = 200;
    	
    	$params=$_REQUEST;
    	
    	if (($username = $_REQUEST['username']) !== null) {
	    	if (($model = User::findByUsername($username)) !== null) {
	    		if (($profile = $model->profile) == null) {
	    			$profile = new Profile();
	    			$profile->user_id = $model->id;
	    			$profile->first_name = $_REQUEST['first_name'];
	    			$profile->last_name = $_REQUEST['last_name'];
	    			$profile->cpf = $_REQUEST['cpf'];
	    			$profile->rg = $_REQUEST['rg'];
	    			$profile->endereco = $_REQUEST['endereco'];
	    			$profile->cep = $_REQUEST['cep'];
	    			$profile->telefone = $_REQUEST['telefone'];
	    			$profile->birthdate = $_REQUEST['birthdate'];
	    			if ($_REQUEST['genero'] == 'Masculino') {
	    				$profile->genero_id = 1;
	    			} else $profile->genero_id = 2;
	    			//return array('status'=> 2, 'data'=>$profile); // Não validou
	    			if ($profile->validate()) {
	    				$profile->save();
	    				return array('status'=> 1, 'data'=>$profile); //ok
	    			} else {
	    				return array('status'=> 2, 'data'=>$profile); // Não validou
	    			}	
	    		} else {
	    			$profile->user_id = $model->id;
	    			$profile->first_name = $_REQUEST['first_name'];
	    			$profile->last_name = $_REQUEST['last_name'];
	    			$profile->cpf = $_REQUEST['cpf'];
	    			$profile->rg = $_REQUEST['rg'];
	    			$profile->endereco = $_REQUEST['endereco'];
	    			$profile->cep = $_REQUEST['cep'];
	    			$profile->telefone = $_REQUEST['telefone'];
	    			$profile->birthdate = $_REQUEST['birthdate'];
	    			if ($_REQUEST['genero'] == 'Masculino') {
	    				$profile->genero_id = 1;
	    			} else $profile->genero_id = 2;
	    			//return array('status'=> 2, 'data'=>$profile); // Não validou
	    			if ($profile->validate()) {
	    				$profile->update();
	    				return array('status'=> 1, 'data'=>$profile); //ok
	    			} else {
	    				return array('status'=> 2, 'data'=>$profile); // Não validou
	    			}
	    		}
	    			
	    	}
    	}
    	return array('status'=> 0, 'data'=>$_REQUEST); //não encontrou usuario
    }
    
}
