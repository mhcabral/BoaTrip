<?php
 
use yii\helpers\Html;
use app\models\PermissionHelpers;
 
/**
 * @var yii\web\View $this
 */
 
$this->title = 'BoaTrip';
if (!Yii::$app->user->isGuest) { 
	$is_admin = PermissionHelpers::requireMinimumRole('Admin');
}
 
?>
 
 
<div class="site-index">
 
    <div class="jumbotron">
    
        <h1>Administração BoaTrip</h1>
 
        <p class="lead">
        
	        Aqui você pode gerenciar Barcos, Viagens / Passagens, Vendas e Outros.
        
        </p>
        
    </div>
 
    <div class="body-content">
 
        <div class="row">
            <div class="col-lg-4">
            
            	<h2>Cadastros</h2>
                <p>
					Este é o lugar para gerenciar usuários, perfis, papeis e status.
                </p>
                <p>
				<?php 
				   if (!Yii::$app->user->isGuest && $is_admin) {
				       echo Html::a('Cadastros', ['cadastro'], ['class' => 'btn btn-default']);
				   } 
				?>
                </p>
     
                <h2>Barcos</h2>
                <p>
					Este é o lugar para gerenciar barcos.
                </p>
                <p>
				<?php 
				   if (!Yii::$app->user->isGuest && $is_admin) {
				       echo Html::a('Gerenciar Barcos', ['barco/index'], ['class' => 'btn btn-default']);
				   } 
				?>
                </p>
            </div>
            
            <div class="col-lg-4">
            	<h2>Viagens</h2>
                <p>
             		Este é o lugar para gerenciar viagens.
                </p>
                <p>
					<?php 
					                        
					     if (!Yii::$app->user->isGuest && $is_admin) {
					                                    
					        echo Html::a('Gerenciar Viagens', ['viagem/index'], ['class' => 'btn btn-default']);
					                                
					     } 
					                        
					?>
               	</p>
               
				<h2>Vendas</h2>
               	<p>
               	Este é o lugar para gerenciar vendas.
                </p>
                <p>
				<?php 
				                        
				     if (!Yii::$app->user->isGuest && $is_admin) {
				                        
				         echo Html::a('Gerenciar Vendas', ['venda/index'], ['class' => 'btn btn-default']);
				                                
				     } 
				                
				?>
				</p>
				                
			</div>
        </div>
    </div>
</div>