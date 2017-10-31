(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentariosContext", "api/999/comentarios");
    mod.controller('comentariosCtrl', ['$scope', '$http', 'comentariosContext',
        function ($scope, $http, comentariosContext, $state) {
            $http.get('data/comentarios.json').then(function (response) {
                $scope.comentariosRecords = response.data;
           });
            
            if ($state.params.comentarioId !== undefined) {
                $http.get(comentarioContext + '/' + $state.params.comentarioId).then(function (response) {
                    $scope.comentariosRecords = response.data.comentario;
                    $scope.currentComentario = response.data;
                });
            }
        }
    ]);
}
)(windows.angular);