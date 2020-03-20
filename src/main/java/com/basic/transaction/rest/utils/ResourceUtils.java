package com.basic.transaction.rest.utils;

import com.basic.transaction.rest.exceptions.BadRequestException;

public class ResourceUtils {

  private ResourceUtils() {
  }

  public static void validateRequiredParameter(String parameterName, String parameterValue) {
    if (isEmptyOrNull(parameterValue)) {
      throw new BadRequestException("Invalid parameter " + parameterName);
    }
  }

  public static void validateRequiredParameter(String parameterName, Object parameterValue) {
    if (parameterValue == null) {
      throw new BadRequestException("Invalid parameter " + parameterName);
    }
  }

  public static void validateOptionalParameter(String parameterName, String parameterValue) {
    if (parameterValue == null) {
      return;
    }
    if (isEmptyOrNull(parameterValue)) {
      throw new BadRequestException("Invalid empty parameter " + parameterName);
    }
  }

  public static boolean isEmptyOrNull(String input) {
	  return input == null || input.isBlank() || input.isEmpty();
  }
}
