(
        function (ng) {
            var mod = ng.module("salasModule");
            mod.constant("salasContext", "api/999/salas");
            mod.controller('salasUpdateCtrl', ['$scope', '$http', 'salasContext', '$state', 'salasContext', '$rootScope',
                function ($scope, $http, salasContext, $state, salasContext, $rootScope) {
                    $rootScope.edit = true;

                    var idSala = $state.params.salaId;

                    //Consulto la sala a editar.
                       console.info(salasContext+'/' + idSala);
                    $http.get(salasContext + '/' + idSala).then(function (response) {
                        var sala = response.data;
                        $scope.salaCapacidad = sala.capacidad;
                    });           
                    

                    $scope.createSala = function () {                   
                        $http.put(salasContext + '/' + idSala, {
                   capacidad: $scope.salaCapacidad
                        }).then(function (response) {                            
                            $state.go('salasList', {salaId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);