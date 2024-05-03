//package com.wmw.frontend.integrador.util;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class ContadorAberturaSistema {
//
//	private File file = new File("contador.txt");
//
//	private static int contador = 1;
//
//	private static boolean arquivoCriado = false;
//	private String path = "contador.txt";
//
////	public ContadorAberturaSistema() {
////		
////		try {
////			
////			if (arquivoCriado == false) {
////				file.createNewFile();
////				try {
////					arquivoCriado = true;
////					DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
////					dataOutputStream.writeInt(contador);
////				} catch (Exception e) {
////				}
////			}
////			
////			if(file.exists()) {
////				DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
////				contador = dataInputStream.readInt();
//// 			} else {
//// 				DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
////				contador++;
//// 				dataOutputStream.writeInt(contador);
////				
//// 			}
////			
////			
////		} catch (Exception e) {
////			
////		}
////		
////	
////
////	}
//
//	public ContadorAberturaSistema() {
//
//		try {
//			// Carrega o valor de arquivoCriado do arquivo de configuração
//			if (file.exists()) {
//				try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
//					arquivoCriado = dataInputStream.readBoolean();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//
//			// Cria o arquivo se necessário e define arquivoCriado como true
//			if (!arquivoCriado) {
//				file.createNewFile();
//				try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
//					dataOutputStream.writeBoolean(true);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				arquivoCriado = true;
//			}
//
//			// Lê o valor atual do contador do arquivo
//			if (file.exists()) {
//				try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
//					contador = dataInputStream.readInt();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void contarAbertura() {
//		try {
//			// Verifica se o arquivo exister
//
//			if (file.exists()) {
//				// Se o arquivo existe, lê o valor atual do contador
//				try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
//					contador = dataInputStream.readInt();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} else {
//				// Se o arquivo não existe, define o contador como 1
//				contador = 1;
//			}
//
//			// Verifica se é a primeira vez que o sistema foi aberto
//			if (contador == 1) {
//				System.out.println("Bem-vindo! Esta é a primeira vez que o sistema foi aberto.");
//			}
//
//			// Incrementa o contador
//			contador++;
//			System.out.println(contador);
//
//		} catch (Exception e) {
//
//		}
//
//	}
//
//}
