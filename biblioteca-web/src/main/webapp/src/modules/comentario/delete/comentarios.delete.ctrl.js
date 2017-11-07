(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.controller('comentarioDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idComentario = $state.params.comentarioId;
            $scope.deleteComentario = function () {
                 $http.delete('api/999/comentarios' + '/' + idComentario, {}).then(function (response) {
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);