(
        function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuarioContext", "api/999/usuarios");
            mod.constant("prestamoContext", "api/books");
            mod.controller('medioPagoUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'prestamoContext', '$rootScope', '$filter',
                function ($scope, $http, usuarioContext, $state, prestamoContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idUsuario = $state.params.usuarioId;
                    var idMedioPago = $state.params.medioPagoId;
                    console.info(idUsuario);
                    console.info(idMedioPago);


                    $scope.updateMedioPago = function () {                   
                        $http.put(usuarioContext + "/" + idUsuario+"/medioPago/"+idMedioPago, {
                            descripcion: $scope.medioPagoDesc,
                            tipo: $scope.medioPagoTip
                        }).then(function (response) {                            
                            $state.go('medioPagoList', {usuarioId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);