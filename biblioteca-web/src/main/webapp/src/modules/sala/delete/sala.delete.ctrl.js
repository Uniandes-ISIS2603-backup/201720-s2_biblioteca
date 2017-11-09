(function (ng) {
    var mod = ng.module("salasModule");
    mod.controller('salasDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idsala= $state.params.salaId;
            $scope.deleteSala = function () {
                 $http.delete('api/999/salas' + '/' + idsala, {}).then(function (response) {
                    $state.go('salasList', {salaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);