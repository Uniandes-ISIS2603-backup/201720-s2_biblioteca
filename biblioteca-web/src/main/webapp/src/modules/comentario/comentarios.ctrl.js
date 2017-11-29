(function (ng) {
    var mod = ng.module("comentariosModule");
    mod.constant("comentariosContext", "api/999/comentario");
    mod.controller('comentariosCtrl', ['$scope', '$http', 'comentariosContext', '$state',
        function ($scope, $http, comentariosContext, $state) {
            $http.get(comentariosContext).then(function (response) {
                $scope.comentarioRecords = response.data;
            });
            
            if ($state.params.comentarioId !== undefined && ($state.params.comentarioId !== null)) {
                $http.get(comentariosContext + '/' + $state.params.comentarioId).then(function (response) {
                      $scope.comentarioRecords = response.data.comentarios;
                  $scope.currentComentario = response.data;
                });
            }
        }
    ]);
}
)(angular);