package vn.truong.warehouse.exception;

public class ResourceNotFoundException extends RuntimeException { public ResourceNotFoundException(String type, Object id) { super(type + " not found: " + id); } }
