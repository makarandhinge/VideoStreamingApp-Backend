package com.stream.app.payload;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomMessage {

  private String message;
  private boolean success = false;
}
