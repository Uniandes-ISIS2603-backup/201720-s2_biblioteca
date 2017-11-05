(function (ng) {
    var mod = ng.module("bibliotecaModule");
    mod.controller('bibliotecaDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idBiblioteca = $state.params.bibliotecaId;
            $scope.deleteBiblioteca = function () {
                 $http.delete('api/999/bibliotecas' + '/' + idBiblioteca, {}).then(function (response) {
                    $state.go('bibliotecasList', {bibliotecaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);