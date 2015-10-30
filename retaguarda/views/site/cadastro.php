<?php
 
use yii\helpers\Html;
use app\models\PermissionHelpers;
 
/**
 * @var yii\web\View $this
 */
 
$this->title = 'BoaTrip - Cadastro';
if (!Yii::$app->user->isGuest) { 
	$is_admin = PermissionHelpers::requireMinimumRole('Admin');
}
 
?>
 
 
<div class="site-index">
 
    <div class="jumbotron">
        <h1>Cadastros</h1>
        <p class="lead">
	        Aqui você pode gerenciar Usuários, Perfis, Papeis, Localidades e Tipos de Passagem.
        </p>
    </div>
 
    <div class="body-content">
 
        <div class="row">
            <div class="col-lg-4">
            
                <h2>Usuários</h2>
                <p>
					Este é o lugar para gerenciar usuários.
                </p>
                <p>
				<?php 
				   if (!Yii::$app->user->isGuest && $is_admin) {
				       echo Html::a('Gerenciar Usuários', ['user/index'], ['class' => 'btn btn-default']);
				   } 
				?>
                </p>
                
                <h2>Papeis</h2>
                <p>
				Este é o lugar para gerenciar usuários.                
                </p>
                <p>
				<?php
     			if (!Yii::$app->user->isGuest && $is_admin) {
        			echo Html::a('Gerenciar Papeis', ['role/index'], ['class' => 'btn btn-default']);              
     			} 
				?>
                </p>
            </div>
            
            <div class="col-lg-4">
                <h2>Perfil</h2>
                <p>
                Atualizar o seu perfil.  
                </p>
                <p>
				<?php 
				     if (!Yii::$app->user->isGuest && $is_admin) {
				         echo Html::a('Atualizar Perfil', ['profile/index'], ['class' => 'btn btn-default']);
				     } 
				?>
                </p>
            
                <h2>Localidades</h2>
                <p>
                Este é o lugar para gerenciar localidades.
                </p>
                <p>
				<?php 
				   if (!Yii::$app->user->isGuest && $is_admin) {
				      echo Html::a('Gerenciar Localidades', ['localidade/index'], ['class' => 'btn btn-default']);
				    } 
				?>
                </p>
            </div>
            
            <div class="col-lg-4">
                <h2>Unidades Federativas</h2>
                <p>
                Este é o lugar para gerenciar unidades federativas.  
                </p>
                <p>
				<?php 
				     if (!Yii::$app->user->isGuest && $is_admin) {
				         echo Html::a('Gerenciar UF', ['uf/index'], ['class' => 'btn btn-default']);
				     } 
				?>
                </p>
            
                <h2>Tipos de Passagens</h2>
                <p>
                Este é o lugar para gerenciar tipos de passagem.
                </p>
                <p>
				<?php 
				   if (!Yii::$app->user->isGuest && $is_admin) {
				      echo Html::a('Gerenciar Tipos de Passagem', ['passagem-tipo/index'], ['class' => 'btn btn-default']);
				    } 
				?>
                </p>
            </div>
            
        </div>
    </div>
</div>