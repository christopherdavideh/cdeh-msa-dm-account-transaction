package com.banking.cdeh_msa_dm_account_transaction.util;

/**
 * Constants for logging messages
 */
public class LogMessages {

    // Transaction Service Messages
    public static final String TRANSACTION_CREATE_REQUEST = "Iniciando creación de transacción para customer: {}";
    public static final String TRANSACTION_CREATE_SUCCESS = "Transacción creada exitosamente con ID: {}";
    public static final String TRANSACTION_CREATE_ERROR = "Error al crear transacción para customer: {}";

    public static final String TRANSACTION_GET_REQUEST = "Obteniendo transacción con ID: {}";
    public static final String TRANSACTION_GET_SUCCESS = "Transacción obtenida exitosamente con ID: {}";
    public static final String TRANSACTION_GET_ERROR = "Error al obtener transacción con ID: {}";

    public static final String TRANSACTION_UPDATE_REQUEST = "Actualizando transacción con ID: {}";
    public static final String TRANSACTION_UPDATE_SUCCESS = "Transacción actualizada exitosamente con ID: {}";
    public static final String TRANSACTION_UPDATE_ERROR = "Error al actualizar transacción con ID: {}";

    public static final String TRANSACTION_DELETE_REQUEST = "Desactivando transacción con ID: {}";
    public static final String TRANSACTION_DELETE_SUCCESS = "Transacción desactivada exitosamente con ID: {}";
    public static final String TRANSACTION_DELETE_ERROR = "Error al desactivar transacción con ID: {}";

    public static final String TRANSACTION_LIST_REQUEST = "Obteniendo lista de transacciones para customer: {}";
    public static final String TRANSACTION_LIST_SUCCESS = "Lista de transacciones obtenida exitosamente. Total: {}";
    public static final String TRANSACTION_LIST_ERROR = "Error al obtener lista de transacciones para customer: {}";

    public static final String TRANSACTION_NOT_FOUND = "Transacción no encontrada con ID: {}";
}
