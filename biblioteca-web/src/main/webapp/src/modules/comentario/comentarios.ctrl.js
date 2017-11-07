(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentarioContext", "api/999/comentarios");
    mod.controller('comentarioCtrl', ['$scope', '$http', 'comentarioContext','$state',
        function ($scope, $http, comentarioContext, $state) {
            $http.get(comentarioContext).then(function (response) {
                $scope.comentarioRecords = response.data;
           });
            
            if ($state.params.comentarioId !== undefined) {
                $http.get(comentarioContext + '/' + $state.params.comentarioId).then(function (response) {
                    $scope.comentarioRecords = response.data.comentario;
                    $scope.currentComentario = response.data;
                });
            }
        }
    ]);
}
)(window.angular);