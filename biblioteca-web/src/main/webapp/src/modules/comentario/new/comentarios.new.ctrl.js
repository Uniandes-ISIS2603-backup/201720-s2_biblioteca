(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentariosContext", "api/999/comentarios");
    mod.controller('comentarioNewCtrl', ['$scope', '$http', 'comentariosContext', '$state', '$rootScope',
        function ($scope, $http, comentariosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createComentario = function () {
                $http.post(comentariosContext, {
                    name: $scope.comentarioName,
                    calificacion: $scope.comentarioCalificacion,
                    fechaPublicado: $scope.fechaPublicado,
                }).then(function (response) {
                    //Comentario created successfully
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(windows.angular);
