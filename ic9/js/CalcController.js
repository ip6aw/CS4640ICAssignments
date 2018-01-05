function CalcController ($scope)
{

$scope.qty = 1; 
$scope.cost = 10; 

$scope.calc = function () {return $scope.qty * $scope.cost}; 
    
 
    //  var ngApp = angular.module('myApp', []);   

      ngApp.controller('CalcController', function($scope) {  
         $scope.compute = function(flag) {
            $scope.score1 = ($scope.prj1 * 100)/30;
            $scope.score2 = $scope.prj2;
            $scope.score3 = $scope.prj3;
            $scope.score4 = $scope.prj4;
            $scope.score5 = ($scope.prj5 * 100)/50;
            $scope.score6 = $scope.prj6;
            
            $scope.sum = $scope.score1 + $scope.score2 + $scope.score3 + $scope.score4 + $scope.score5 + $scope.score6;
            $scope.lowest = Math.min($scope.score1, $scope.score2, $scope.score3, $scope.score4, $scope.score5, $scope.score6);
            
            $scope.flag = flag;
            $scope.avg = 0;
            if ($scope.flag == "drop")
            {
               $scope.flag = "compute with drop";
               $scope.sum -= $scope.lowest;
               $scope.avg = $scope.sum / 5;
            }
            else
            {
               $scope.flag = "compute without drop";
               $scope.avg = $scope.sum / 6;
            }
         };
         
         
      }); 


}