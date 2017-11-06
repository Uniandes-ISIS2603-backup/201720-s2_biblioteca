(
        function (ng) {
            var mod = ng.module("reservaModule");
            mod.constant("usuarioContext", "api/999/usuarios");
            mod.constant("reservaContext", "api/books");
            mod.controller('reservaUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'reservaContext', '$rootScope', '$filter',
                function ($scope, $http, usuarioContext, $state,reservaContext, $rootScope, $filter) {
                    $rootScope.edit = true;       
                    
                     var idReserva= $state.params.reservaId;
                    var idUsuario = $state.params.usuarioId;
                    console.info(usuarioContext+'/' + idUsuario);
                    $http.get('api/999/usuarios/' + $state.params.usuarioId+'/reservas/'+idReserva).then(function (response) {
                        var prest = response.data;
                        $scope.completada = prest.completada;
                       
                    });     
                                        

                    $scope.updateReserva = function () {                   
                        $http.put('api/999/usuarios/' + $state.params.usuarioId+'/reservas/'+idReserva, {
                            completada: $scope.completada,
                            
                        }).then(function (response) {                            
                            $state.go('reservaList', {reservaId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);