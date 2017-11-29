(function (ng) {
    var mod = ng.module("comentariosModule");
    mod.constant("comentariosContext", "api/999/comentario");
    mod.controller('comentarioNewCtrl', ['$scope', '$http', 'comentariosContext', '$state', '$rootScope',
        function ($scope, $http, comentariosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createComentario = function () {
                $http.post(comentariosContext, {
                    name: $scope.comentarioName,
                   calificacion: $scope.comentarioCalificacion,
                   comentarioC: $scope.comentarioC,
                }).then(function (response) {
                    //Comentario created successfully
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
