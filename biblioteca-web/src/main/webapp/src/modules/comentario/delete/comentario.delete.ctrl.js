(function (ng) {
    var mod = ng.module("comentariosModule");
    mod.controller('comentariosDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idComentario= $state.params.comentarioId;
            $scope.deleteComentario = function () {
                 $http.delete('api/999/comentarios' + '/' + idComentario, {}).then(function (response) {
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);